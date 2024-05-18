package com.sonnguyen.individual.nhs.WebController.User;

import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Loan;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;
import com.sonnguyen.individual.nhs.Service.IService.ILoanService;
import com.sonnguyen.individual.nhs.Service.IService.ILoginService;
import com.sonnguyen.individual.nhs.Utils.OTPUtils;
import com.sonnguyen.individual.nhs.Utils.RequestUtils;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sonnguyen.individual.nhs.Utils.Constants.OTP;
import static com.sonnguyen.individual.nhs.Utils.Constants.PIN;
import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name ="loan-create",urlPatterns = "/app/loan/create")
public class CreateLoanController extends HttpServlet {
    @Inject
    OTPUtils otpUtils;
    @Inject
    ICustomerService customerService;
    @Inject
    IAccountService accountService;
    @Inject
    ILoanService loanService;

    @Inject
    ILoginService loginService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer=SessionUtils.getPrincipal(req).getCustomer();
        req.getRequestDispatcher("/page/user/EnterPin/page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login account= SessionUtils.getPrincipal(req);
        if(req.getParameter(PIN) != null){
            if(loginService.validatePIN(account.getId(),req.getParameter(PIN))){
                otpUtils.generateOTP().sendToEmail("hellohoangson@gmail.com").sessionSave(req);
                req.getRequestDispatcher("/page/user/EnterOTP/page.jsp").forward(req,resp);
            }
            else{
                req.setAttribute(ERROR_MESSAGE,"Invalid PIN");
                req.getRequestDispatcher("/page/user/EnterPin/page.jsp").forward(req,resp);
            }
        }
        if(req.getParameter(OTP) != null){
            String otp= (String) SessionUtils.getSession(req,OTP);
            if(req.getParameter(OTP).equals(otp)){
                req.setAttribute("accounts",accountService.findAllByCustomerId(account.getCustomerId()));
                req.getRequestDispatcher("/page/user/LoanCreate/page.jsp").forward(req,resp);
            }else {
                req.setAttribute(ERROR_MESSAGE,"Invalid OTP");
                req.getRequestDispatcher("/page/user/EnterOTP/page.jsp").forward(req,resp);
            }
        }
        if(req.getParameter("LoanCreate")!=null){
            Loan loan=RequestUtils.parseEntity(req, Loan.class);
            loan.setCustomerId(account.getCustomerId());
            loan.setBranchId(1);
            loanService.save(loan);
            req.setAttribute("loan",loan);
            req.getRequestDispatcher("/page/user/LoanSuccess/page.jsp").forward(req,resp);
        }
    }
}
