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
import com.sonnguyen.individual.nhs.service.iservice.ISavingsSettingService;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Model
@WebServlet(name = "savings/create",urlPatterns = "/app/saving/create")
public class CreateSavingsController extends HttpServlet {
    @Inject
    private IAccountService accountService;
    @Inject
    private ICustomerService customerService;
    @Inject
    private IMembershipService membershipService;
    @Inject
    private SecurityContextHolder securityContextHolder;
    @Inject
    private ISavingsSettingService savingsSettingService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SavingsSetting> savingsSettings=savingsSettingService.findAll();
        UserDetailImp userDetailImp= (UserDetailImp) securityContextHolder.getPrincipal();
        Customer customer=customerService.findById(userDetailImp.getCustomerId()).get();
        List<Account> accounts = new ArrayList<>(accountService.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.PRIMARY, userDetailImp.getCustomerId()));
        req.setAttribute("savingsSettings", savingsSettings);
        req.setAttribute("accounts",accounts);
        req.setAttribute("membership",membershipService.findById(customer.getMembershipID()).orElse(new Membership()));
        req.getRequestDispatcher("/page/user/SavingAccountCreate/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("OTP")!=null&&req.getAttribute("OTP").equals("VALID")){
            UserDetailImp login= (UserDetailImp) securityContextHolder.getPrincipal();
            try{
                SavingsInfo savingsInfo= (SavingsInfo) SessionUtils.getSession(req,"savingsInfo");
                System.out.println(savingsInfo);
                savingsSettingService.findById(savingsInfo.getTerm_id()).ifPresentOrElse(savingsSetting -> {
                    savingsInfo.setInterestRate(BigDecimal.valueOf(savingsSetting.getInterestRate()));
                    savingsInfo.setTerm(savingsSetting.getTerm());
                },()->{
                    throw new IllegalArgumentException("Illegal request parameter");
                });
                accountService.createSavingsAccount(login.getCustomerId(), savingsInfo);
                req.setAttribute("result",new Result(Message.Type.SUCCESS,"Saving account created successfully",HttpStatus.SC_OK ));
                req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req,resp);
            }catch (Exception e){
                e.printStackTrace();
                req.setAttribute("result",new Result(Message.Type.ERROR,"Error",HttpStatus.SC_OK ));
                req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req,resp);
            }
            return;
        }
        SavingsInfo savingsInfo=RequestUtils.parseEntity(req,SavingsInfo.class);
        SessionUtils.setSession(req,"savingsInfo",savingsInfo);
        SessionUtils.setSession(req,"endpoint","/app/saving/create");
        resp.sendRedirect(req.getContextPath()+"/app/pin");
        return;
    }
}
