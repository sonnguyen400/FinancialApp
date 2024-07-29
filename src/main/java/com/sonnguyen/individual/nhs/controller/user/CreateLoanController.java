package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.DefaultBrand;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.model.Membership;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import com.sonnguyen.individual.nhs.service.iservice.ILoanService;
import com.sonnguyen.individual.nhs.service.iservice.IMembershipService;
import com.sonnguyen.individual.nhs.utils.RequestUtils;
import com.sonnguyen.individual.nhs.utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name ="loan-create",urlPatterns = "/app/loan/create")
public class CreateLoanController extends HttpServlet {
    @Inject
    ICustomerService customerService;
    @Inject
    IAccountService accountService;
    @Inject
    ILoanService loanService;
    @Inject
    IMembershipService membershipService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getRequestURI().equalsIgnoreCase("/app/otp")){
            Login account= SessionUtils.getPrincipal(req);
            req.setAttribute("accounts",accountService.findPrincipleByCustomerId(account.getCustomer().getId()));
            Customer customer=customerService.findById(account.getCustomerId());
            req.setAttribute("membership",membershipService.findById(customer.getMembershipID()).orElse(new Membership()));
            req.getRequestDispatcher("/page/user/LoanCreate/page.jsp").forward(req, resp);
            return;
        }
        req.getSession().setAttribute("endpoint", "/app/loan/create");
        req.getRequestDispatcher("/app/pin").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!Objects.equals(req.getRequestURI(), "/app/otp")){
            resp.sendRedirect(req.getContextPath()+"/");
        }else{
            Login account= SessionUtils.getPrincipal(req);
            if(req.getParameter("LoanCreate")!=null){
                Loan loan=RequestUtils.parseEntity(req, Loan.class);
                loan.setCustomerId(account.getCustomerId());
                loan.setBranchId(DefaultBrand.ID.value);
                loanService.save(loan);
                req.setAttribute("loan",loan);
                req.getRequestDispatcher("/page/user/LoanSuccess/page.jsp").forward(req,resp);
            }
            doGet(req,resp);
        }
    }
}
