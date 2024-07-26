package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.service.iservice.ILoginService;
import com.sonnguyen.individual.nhs.utils.OTPUtils;
import com.sonnguyen.individual.nhs.utils.SessionUtils;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/EnterPin/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login account= SessionUtils.getPrincipal(req);
        if(req.getParameter(PIN) != null&&loginService.validatePIN(account.getId(),req.getParameter(PIN))){
            otpUtils.generateOTP().sessionSave(req).sendToEmail("hellohoangson@outlook.com");
            req.getRequestDispatcher("/app/otp").include(req,resp);
            return;
        } else if(req.getRequestURI().equalsIgnoreCase("/app/pin")){
            req.setAttribute(ERROR_MESSAGE,"Invalid PIN");
        }
        doGet(req,resp);
    }

}
