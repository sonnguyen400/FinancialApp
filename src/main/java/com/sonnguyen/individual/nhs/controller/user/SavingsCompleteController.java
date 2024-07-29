package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.dao.impl.SavingDAOImp;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.SavingsInfo;
import com.sonnguyen.individual.nhs.service.AccountService;
import com.sonnguyen.individual.nhs.service.SavingInfoService;
import org.apache.velocity.exception.ResourceNotFoundException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "savings-complete",urlPatterns = "/app/saving/complete")
public class SavingsCompleteController extends HttpServlet {
    @Inject
    private SavingInfoService savingInfoService;
    @Inject
    private AccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int savingsAccountId;
        SavingsInfo savingsInfo;
        Account linkedAccount;
        try{
            savingsAccountId=Integer.parseInt(req.getParameter("id"));
            savingsInfo=savingInfoService.findById(savingsAccountId).orElseThrow(()->new ResourceNotFoundException("Savings Account Not Found"));
            linkedAccount=accountService.findById(savingsInfo.getAccountId()).orElseThrow(()->new ResourceNotFoundException("Account Not Found"));
        } catch (NumberFormatException|NullPointerException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("linkedAccount",linkedAccount);
        req.setAttribute("savings",savingsInfo);
        req.getRequestDispatcher("/page/user/SavingsComplete/page.jsp");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer distributedAccount= Integer.valueOf(req.getParameter("distributedAccount"));
    }
}
