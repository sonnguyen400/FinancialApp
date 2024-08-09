package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.utils.OTPUtils;
import com.sonnguyen.individual.nhs.utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sonnguyen.individual.nhs.utils.Constants.OTP;
import static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "otp-validate",urlPatterns = "/app/otp")
public class OTPController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/EnterOTP/page.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter(OTP) != null){
            if(OTPUtils.isValid(req)){
                req.getRequestDispatcher((String) SessionUtils.getSession(req,"endpoint")).include(req,resp);
            }else {
                System.out.println("Invalid OTP");
                req.setAttribute(ERROR_MESSAGE,"Invalid OTP");
                doGet(req,resp);
            }
        }else{
            doGet(req,resp);
        }
    }

}
