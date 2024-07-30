package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.dto.Message;
import com.sonnguyen.individual.nhs.dto.Result;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.model.SavingsInfo;
import com.sonnguyen.individual.nhs.service.AccountService;
import com.sonnguyen.individual.nhs.service.SavingInfoService;
import com.sonnguyen.individual.nhs.utils.SessionUtils;
import org.apache.http.HttpStatus;
import org.apache.velocity.exception.ResourceNotFoundException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "savings-complete",urlPatterns = "/app/saving/complete")
public class SavingsCompleteController extends HttpServlet {
    @Inject
    private SavingInfoService savingInfoService;
    @Inject
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login= SessionUtils.getPrincipal(req);
        int savingsAccountId;
        SavingsInfo savingsInfo;
        Account linkedAccount;
        List<Account> accounts;
        try{
            accounts = new ArrayList<>(accountService.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.PRIMARY, login.getCustomerId()));
            savingsAccountId=Integer.parseInt(req.getParameter("id"));
            savingsInfo=savingInfoService.findById(savingsAccountId).orElseThrow(()->new ResourceNotFoundException("Savings Account Not Found"));
            linkedAccount=accountService.findById(savingsInfo.getAccountId()).orElseThrow(()->new ResourceNotFoundException("Account Not Found"));
        } catch (NumberFormatException|NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        req.setAttribute("accounts",accounts);
        req.setAttribute("linkedAccount",linkedAccount);
        req.setAttribute("savings",savingsInfo);
        req.getRequestDispatcher("/page/user/SavingsComplete/page.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getRequestURI().contains("/app/otp")){
            System.out.println();
            SessionUtils.setSession(req,"beneficiary_account_id",req.getParameter("beneficiary_account_id"));
            SessionUtils.setSession(req,"savings_id",req.getParameter("savings_id"));
            SessionUtils.setSession(req,"endpoint",req.getContextPath()+"/app/saving/complete");
            resp.sendRedirect(req.getContextPath()+"/app/pin");
            return;
        }
        try{
            int beneficiary_account_id=Integer.parseInt((String) SessionUtils.getSession(req,"beneficiary_account_id"));
            SavingsInfo savingsInfo=savingInfoService.findById(Integer.parseInt((String) SessionUtils.getSession(req,"savings_id"))).orElseThrow(()-> new ResourceNotFoundException("Can't not found resource name savings"));
            savingInfoService.completeSavings(savingsInfo,beneficiary_account_id);
            req.setAttribute("result",new Result(Message.Type.SUCCESS,"Success", HttpStatus.SC_OK));
            req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req,resp);
        }catch(Exception exception){
            exception.printStackTrace();
            req.setAttribute("result",new Result(Message.Type.ERROR, exception.getMessage(), HttpStatus.SC_OK));
            req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req,resp);
            return;
        }



    }


}
