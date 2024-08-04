package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Membership;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import com.sonnguyen.individual.nhs.service.iservice.IMembershipService;
import org.apache.velocity.exception.ResourceNotFoundException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "personal", urlPatterns = "/app/personal")
public class PersonalController extends HttpServlet {
    @Inject
    ICustomerService customerService;
    @Inject
    SecurityContextHolder securityContextHolder;
    @Inject
    IMembershipService membershipService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDetailImp userDetailImp= (UserDetailImp) securityContextHolder.getPrincipal();
        Customer customer=customerService.findById(userDetailImp.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Membership membership=membershipService.findById(customer.getMembershipID()).orElse(null);
        req.setAttribute("customer", customer);
        req.setAttribute("membership", membership);
        req.getRequestDispatcher("/page/user/Personal/page.jsp").forward(req, resp);
    }
}
