package com.sonnguyen.individual.nhs.WebController;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Utils.RequestUtils;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;
import static com.sonnguyen.individual.nhs.Utils.SessionUtils.LOGIN_SESSION;

@WebServlet(name = "login",urlPatterns = {"/login","/logout"})

public class LoginController extends HttpServlet {

    @Inject
    IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/base/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account= RequestUtils.parseEntity(req,Account.class);
        Optional<Account> account_=accountService.findByUsername(account.getUsername());
        if(account_.isPresent()){
            if(account_.get().getPassword().equals(account.getPassword())){
                SessionUtils.setSession(req,LOGIN_SESSION,account_.get());
                try {
                    resp.sendRedirect(req.getContextPath()+"/");
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        req.setAttribute(ERROR_MESSAGE,"Invalid password");
        req.getRequestDispatcher("/page/base/login.jsp").forward(req,resp);
    }
}
