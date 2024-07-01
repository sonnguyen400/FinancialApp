package com.sonnguyen.individual.nhs.WebController.User;

import com.sonnguyen.individual.nhs.Constant.DefaultBrand;
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
import java.util.ArrayList;
import java.util.List;

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
