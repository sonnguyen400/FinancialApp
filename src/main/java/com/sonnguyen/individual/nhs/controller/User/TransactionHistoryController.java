package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.model.Transaction;
import com.sonnguyen.individual.nhs.service.iService.IAccountService;
import com.sonnguyen.individual.nhs.service.iService.ITransactionService;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "transaction-history",urlPatterns = "/app/history")
public class TransactionHistoryController extends HttpServlet {
    @Inject
    ITransactionService transactionService;
    @Inject
    IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login= SessionUtils.getPrincipal(req);
        Account defaultAcc=accountService.findDefaultAccountByCustomerId(login.getCustomerId());
        List<Transaction> transactionList=transactionService.findHistoryByAccountId(defaultAcc.getId());
        req.setAttribute("defaultAccount",defaultAcc);
        req.setAttribute("transactions",transactionList);
        req.getRequestDispatcher("/page/user/TransactionHistory/page.jsp").forward(req, resp);
    }
}
