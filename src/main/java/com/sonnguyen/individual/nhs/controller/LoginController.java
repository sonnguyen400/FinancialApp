package com.sonnguyen.individual.nhs.controller;

import com.sonnguyen.individual.nhs.exception.AuthenticationException;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import com.sonnguyen.individual.nhs.service.iservice.ILoginService;
import com.sonnguyen.individual.nhs.utils.RequestUtils;
import com.sonnguyen.individual.nhs.utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE;
import static com.sonnguyen.individual.nhs.utils.SessionUtils.LOGIN_SESSION;

@WebServlet(name = "login",urlPatterns = {"/login","/logout"})
public class LoginController extends HttpServlet {
    @Inject
    ILoginService loginService;
    @Inject
    ICustomerService customerService;
    @Inject
    SecurityContextHolder securityContextHolder;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute(LOGIN_SESSION);
        req.getRequestDispatcher("/page/base/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            UserDetailImp userDetailImp=(UserDetailImp) securityContextHolder.authenticate(req.getParameter("username"),req.getParameter("password"));
            userDetailImp.setCustomer(customerService.findById(userDetailImp.getCustomerId()).get());
            SessionUtils.setSession(req,LOGIN_SESSION,userDetailImp);
            resp.sendRedirect(req.getContextPath()+"/");
            return;
        } catch (AuthenticationException e) {
            req.setAttribute(ERROR_MESSAGE,e.getMessage());
            doGet(req,resp);
        }

    }
}
