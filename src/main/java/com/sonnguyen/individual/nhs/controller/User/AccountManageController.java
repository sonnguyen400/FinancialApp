package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "account-manage",urlPatterns = "/app/account/manage")
public class AccountManageController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> accounts=accountService.findPrincipleByCustomerId(SessionUtils.getPrincipal(req).getCustomerId());
        req.setAttribute("accounts",accounts);
        req.setAttribute("sum",accounts.stream().reduce(BigDecimal.ZERO,(pre,acc)->acc.getBalance().add(pre),BigDecimal::add));
        req.getRequestDispatcher("/page/user/AccountManage/page.jsp").forward(req,resp);
    }
}
