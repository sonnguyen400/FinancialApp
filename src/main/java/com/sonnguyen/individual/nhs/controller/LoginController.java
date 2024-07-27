package com.sonnguyen.individual.nhs.controller;

import com.sonnguyen.individual.nhs.model.Login;
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
    IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute(LOGIN_SESSION);
        req.getRequestDispatcher("/page/base/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login_= RequestUtils.parseEntity(req,Login.class);
        Login login= loginService.validateLogin(login_.getUsername(), login_.getPassword());
        if(login!=null){
            login.setCustomer(customerService.findById(login.getCustomerId()));
            SessionUtils.setSession(req,LOGIN_SESSION,login);
                try {
                    resp.sendRedirect(req.getContextPath()+"/");
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
        req.setAttribute(ERROR_MESSAGE,"Invalid password");
        req.getRequestDispatcher("/page/base/login.jsp").forward(req,resp);
    }
}
