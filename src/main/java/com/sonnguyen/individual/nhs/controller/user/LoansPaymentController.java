package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.dto.Message;
import com.sonnguyen.individual.nhs.dto.Result;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.model.Payment;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
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
    @Inject
    SecurityContextHolder securityContextHolder;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDetailImp userDetailImp= (UserDetailImp) securityContextHolder.getPrincipal();
        int loanId=Integer.parseInt(req.getParameter("id"));
        Loan loan=loanService.findById(loanId).orElseThrow(()->new RedisNoScriptException("Invalid arguments"));
        List<Account> accounts=accountService.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.PRIMARY, userDetailImp.getCustomerId());
        BigDecimal paymentAmount=paymentService.calculateMonthlyPayment(loan);
        req.setAttribute("amount", paymentAmount);
        req.setAttribute("loan", loan);
        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("/page/user/LoanPayment/page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("OTP")!=null&&req.getAttribute("OTP").equals("VALID")) {
            int loanId=(int) SessionUtils.getSession(req,"id");
            int account_id=(int) SessionUtils.getSession(req,"account_id");
            try{
                Payment payment= paymentService.createPayment(loanId,account_id);
                System.out.println(payment);
                req.setAttribute("result", new Result(Message.Type.SUCCESS,"Payment was successful",500));
                req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("result", new Result(Message.Type.ERROR,e.getMessage(),500));
                req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req, resp);
            }
            return;
        }
        SessionUtils.setSession(req, "id",Integer.valueOf(req.getParameter("id")));
        SessionUtils.setSession(req,"account_id",Integer.valueOf(req.getParameter("account_id")));
        SessionUtils.setSession(req, "endpoint", "/app/loan/payment");
        resp.sendRedirect(req.getContextPath() + "/app/pin");
        return;

    }
}
