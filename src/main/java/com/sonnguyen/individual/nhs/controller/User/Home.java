package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.service.iService.IAccountService;
import com.sonnguyen.individual.nhs.service.iService.ICustomerService;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user-home",urlPatterns = "/app/home")
public class Home extends HttpServlet {
    private final Logger logger= Logger.getLogger(this.getClass().getName());
    @Inject
    ICustomerService customerService;
    @Inject
    IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login=SessionUtils.getPrincipal(req);
        Account defaultAccount=accountService.findDefaultAccountByCustomerId(login.getCustomerId());
        req.setAttribute("account", defaultAccount);
        req.getRequestDispatcher("/page/user/HomePage/page.jsp").forward(req,resp);
    }
}
