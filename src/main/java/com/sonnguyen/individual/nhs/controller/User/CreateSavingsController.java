package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Model.SavingsInfo;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Utils.RequestUtils;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;
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
import java.util.Set;

import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "create-savings",urlPatterns = "/app/saving/create")
@Model
public class CreateSavingsController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login= SessionUtils.getPrincipal(req);
        req.setAttribute("accounts",accountService.findAllByCustomerId(login.getCustomerId()));
        req.getRequestDispatcher("/page/user/SavingAccountCreate/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getRequestURI().equalsIgnoreCase("/app/otp")){
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
            Login login= SessionUtils.getPrincipal(req);
            try{
                SavingsInfo savingsInfo= (SavingsInfo) SessionUtils.getSession(req,"savingsInfo");
                accountService.createSavingsAccount(login.getCustomerId(), savingsInfo);
                req.setAttribute("status",1);
                req.setAttribute("message","Saving account created successfully");
                req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req,resp);
            }catch (Exception e){
                req.setAttribute("accounts",accountService.findAllByCustomerId(login.getCustomerId()));
                req.setAttribute(ERROR_MESSAGE,"Error while create saving");
                doGet(req,resp);
            }
        }
    }
}
