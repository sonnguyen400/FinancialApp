package com.sonnguyen.individual.nhs.WebController.User;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "user-home",urlPatterns = "/home")
public class Home extends HttpServlet {
    private final Logger logger= Logger.getLogger(this.getClass().getName());
    @Inject
    ICustomerService customerService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account= (Account) SessionUtils.getSession(req,SessionUtils.LOGIN_SESSION);
        try {
            if(account!=null){
                req.setAttribute("customers",customerService.findAllByAccountId(account.getId()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/page/user/HomePage/page.jsp").forward(req,resp);
    }
}
