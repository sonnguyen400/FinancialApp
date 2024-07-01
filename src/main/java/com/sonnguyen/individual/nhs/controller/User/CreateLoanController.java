package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.constant.DefaultBrand;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.service.iService.IAccountService;
import com.sonnguyen.individual.nhs.service.iService.ICustomerService;
import com.sonnguyen.individual.nhs.service.iService.ILoanService;
import com.sonnguyen.individual.nhs.service.iService.ILoginService;
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
        Login account= SessionUtils.getPrincipal(req);
        if(req.getAttribute("validOTP")!=null&&(boolean)req.getAttribute("validOTP")){
            req.setAttribute("accounts",accountService.findAllByCustomerId(account.getCustomer().getId()));
            req.getRequestDispatcher("/page/user/LoanCreate/page.jsp").forward(req,resp);
        }else{
            req.getSession().setAttribute("endpoint", "/app/loan/create");
            req.getRequestDispatcher("/pin").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("validOTP")!=null&&(boolean)req.getAttribute("validOTP")){
            doGet(req,resp);
        }

        Login account= SessionUtils.getPrincipal(req);
        if(req.getParameter("LoanCreate")!=null){
            Loan loan=RequestUtils.parseEntity(req, Loan.class);
            loan.setCustomerId(account.getCustomerId());
            loan.setBranchId(DefaultBrand.ID.getValue());
            loanService.save(loan);
            req.setAttribute("loan",loan);
            req.getRequestDispatcher("/page/user/LoanSuccess/page.jsp").forward(req,resp);
        }
    }
}
