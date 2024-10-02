package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import com.sonnguyen.individual.nhs.service.iservice.ILoginService;
import com.sonnguyen.individual.nhs.utils.OTPUtils;
import org.apache.velocity.exception.ResourceNotFoundException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sonnguyen.individual.nhs.utils.Constants.PIN;
import static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "pin-validate",urlPatterns = "/app/pin")
public class PINController extends HttpServlet {
    @Inject
    ILoginService loginService;
    @Inject
    OTPUtils otpUtils;
    @Inject
    ICustomerService customerService;
    @Inject
    SecurityContextHolder securityContextHolder;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/EnterPin/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDetailImp userDetailImp= (UserDetailImp) securityContextHolder.getPrincipal();
        Customer customer=customerService.findById(userDetailImp.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Cannot find customer with id "+userDetailImp.getCustomerId()));
        if(req.getParameter(PIN) != null&&loginService.validatePIN(userDetailImp.getId(), req.getParameter(PIN))){
            otpUtils.generateOTP().sessionSave(req).sendToEmail(customer.getEmail());
            req.getRequestDispatcher("/app/otp").include(req,resp);
            return;
        } else if(req.getRequestURI().equalsIgnoreCase("/app/pin")){
            req.setAttribute(ERROR_MESSAGE,"Invalid PIN");
        }
        doGet(req,resp);
    }

}
