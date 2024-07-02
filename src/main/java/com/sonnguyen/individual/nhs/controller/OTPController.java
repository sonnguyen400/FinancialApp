package com.sonnguyen.individual.nhs.controller;

import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sonnguyen.individual.nhs.Utils.Constants.OTP;
import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "otp-validate",urlPatterns = "/app/otp")
public class OTPController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/EnterOTP/page.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getRequestURI().equalsIgnoreCase("/app/pin")){
            doGet(req,resp);
            return;
        }
        if(req.getParameter(OTP) != null){
            String otp= (String) SessionUtils.getSession(req,OTP);
            System.out.println(otp);
            if(req.getParameter(OTP).equals(otp)){
                req.getRequestDispatcher((String) SessionUtils.getSession(req,"endpoint")).include(req,resp);
            }else {
                req.setAttribute(ERROR_MESSAGE,"Invalid OTP");
                doGet(req,resp);
            }
        }
    }

}
