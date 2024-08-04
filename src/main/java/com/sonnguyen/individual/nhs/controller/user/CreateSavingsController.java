package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.dto.Message;
import com.sonnguyen.individual.nhs.dto.Result;
import com.sonnguyen.individual.nhs.model.*;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import com.sonnguyen.individual.nhs.service.iservice.IMembershipService;
import com.sonnguyen.individual.nhs.utils.RequestUtils;
import com.sonnguyen.individual.nhs.utils.SessionUtils;
import org.apache.http.HttpStatus;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "savings/create",urlPatterns = "/app/saving/create")
@Model
public class CreateSavingsController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Inject
    ICustomerService customerService;
    @Inject
    IMembershipService membershipService;
    @Inject
    SecurityContextHolder securityContextHolder;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDetailImp userDetailImp= (UserDetailImp) securityContextHolder.getPrincipal();
        req.setAttribute("accounts",accountService.findAllByCustomerId(userDetailImp.getCustomerId()));
        Customer customer=customerService.findById(userDetailImp.getCustomerId()).get();
        List<Account> accounts = new ArrayList<>(accountService.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.PRIMARY, userDetailImp.getCustomerId()));
        req.setAttribute("accounts",accounts);
        req.setAttribute("membership",membershipService.findById(customer.getMembershipID()).orElse(new Membership()));
        req.getRequestDispatcher("/page/user/SavingAccountCreate/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getRequestURI().equalsIgnoreCase(req.getContextPath()+"/app/otp")){
            SavingsInfo savingsInfo=RequestUtils.parseEntity(req,SavingsInfo.class);
            try(ValidatorFactory factory= Validation.buildDefaultValidatorFactory();) {
                Validator validator=factory.getValidator();
                Set<ConstraintViolation<SavingsInfo>> constraintViolationSet=validator.validate(savingsInfo);
                if(!constraintViolationSet.isEmpty()){
                    constraintViolationSet.forEach(System.out::println);
                    req.setAttribute(ERROR_MESSAGE, new ArrayList<>(constraintViolationSet).get(0).getMessage());
                    doGet(req,resp);
                    return;
                }
            }
            SessionUtils.setSession(req,"savingsInfo",savingsInfo);
            SessionUtils.setSession(req,"endpoint","/app/saving/create");
            req.getRequestDispatcher("/app/pin").include(req,resp);
        }else{
            UserDetailImp login= (UserDetailImp) securityContextHolder.getPrincipal();
            try{
                SavingsInfo savingsInfo= (SavingsInfo) SessionUtils.getSession(req,"savingsInfo");
                accountService.createSavingsAccount(login.getCustomerId(), savingsInfo);
                req.setAttribute("result",new Result(Message.Type.SUCCESS,"Saving account created successfully",HttpStatus.SC_OK ));
                req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req,resp);
            }catch (Exception e){
                e.printStackTrace();
                req.setAttribute("accounts",accountService.findAllByCustomerId(login.getCustomerId()));
                req.setAttribute(ERROR_MESSAGE,e.getMessage());
                doGet(req,resp);
            }
        }
    }
}
