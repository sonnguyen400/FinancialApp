package com.sonnguyen.individual.nhs.Controller.User;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "transfer",urlPatterns = "/transfer")
public class Transfer extends HttpServlet {
    @Inject
    ICustomerService customerService;
    @Inject
    IAccountService accountService;
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
        req.getRequestDispatcher("/page/user/Transfer/page.jsp").forward(req,resp);
    }
}
