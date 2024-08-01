package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ILoanService;
import com.sonnguyen.individual.nhs.service.iservice.IPaymentService;
import com.sonnguyen.individual.nhs.utils.SessionUtils;
import io.lettuce.core.RedisNoScriptException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "loan/payment",urlPatterns = "/app/loan/payment")
public class LoansPaymentController extends HttpServlet {
    @Inject
    ILoanService loanService;
    @Inject
    IPaymentService paymentService;
    @Inject
    IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login=SessionUtils.getPrincipal(req);
        int loanId=Integer.parseInt(req.getParameter("id"));
        Loan loan=loanService.findById(loanId).orElseThrow(()->new RedisNoScriptException("Invalid arguments"));
        List<Account> accounts=accountService.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.PRIMARY, login.getCustomerId());
        BigDecimal paymentAmount=paymentService.calculateMonthlyPayment(loan);
        req.setAttribute("amount", paymentAmount);
        req.setAttribute("loan", loan);
        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("/page/user/LoanPayment/page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURI());
        if(req.getRequestURI().contains(req.getContextPath()+"/app/otp")) {
            SessionUtils.setSession(req, "loan", loanService.findById(Integer.parseInt(req.getParameter("loanId"))));
            SessionUtils.setSession(req, "endpoint", "/loan/payment");
            resp.sendRedirect(req.getContextPath() + "/app/otp");
        }

    }
}
