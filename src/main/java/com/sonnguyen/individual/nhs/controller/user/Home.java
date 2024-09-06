package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "home",urlPatterns = "/app")
public class Home extends HttpServlet {
    private final Logger logger= Logger.getLogger(this.getClass().getName());
    @Inject
    SecurityContextHolder securityContextHolder;
    @Inject
    IAccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDetailImp userDetailImp = (UserDetailImp) securityContextHolder.getPrincipal();
        Account defaultAccount=accountService.findDefaultAccountByCustomerId(userDetailImp.getCustomerId());
        req.setAttribute("account", defaultAccount);
        req.getRequestDispatcher("/page/user/HomePage/page.jsp").forward(req,resp);
    }
}
