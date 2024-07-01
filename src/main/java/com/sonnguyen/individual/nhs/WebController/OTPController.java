package com.sonnguyen.individual.nhs.WebController;

import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sonnguyen.individual.nhs.Utils.Constants.OTP;
import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "otp-validate",urlPatterns = "/otp")
public class OTPController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/EnterOTP/page.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getRequestURI().equalsIgnoreCase("/otp")){
            doGet(req,resp);
        }
        if(req.getParameter(OTP) != null){
            String otp= (String) SessionUtils.getSession(req,OTP);
            if(req.getParameter(OTP).equals(otp)){
                req.setAttribute("validOTP",true);
                req.getRequestDispatcher((String) SessionUtils.getSession(req,"endpoint")).forward(req,resp);
            }else {
                req.setAttribute(ERROR_MESSAGE,"Invalid OTP");
                req.getRequestDispatcher("/page/user/EnterOTP/page.jsp").forward(req,resp);
            }
        }
    }

}
