package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Membership;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import com.sonnguyen.individual.nhs.service.iservice.ILoanService;
import com.sonnguyen.individual.nhs.service.iservice.IMembershipService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loan/manage",urlPatterns = "/app/loan")
public class LoanManageController extends HttpServlet {
    @Inject
    ILoanService loanService;
    @Inject
    ICustomerService customerService;
    @Inject
    IMembershipService membershipService;
    @Inject
    SecurityContextHolder securityContextHolder;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDetailImp userDetailImp= (UserDetailImp) securityContextHolder.getPrincipal();
        req.setAttribute("loans",loanService.findAllByCustomerId(userDetailImp.getCustomerId()));
        Customer customer=customerService.findById(userDetailImp.getCustomerId()).get();
        req.setAttribute("membership",membershipService.findById(customer.getMembershipID()).orElse(new Membership()));
        req.getRequestDispatcher("/page/user/LoanManage/page.jsp").forward(req, resp);
    }
}
