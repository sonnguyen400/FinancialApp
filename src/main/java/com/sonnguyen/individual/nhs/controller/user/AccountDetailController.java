package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Tier;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ITierService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
@WebServlet(name = "account/detail",urlPatterns = "/app/account")
public class AccountDetailController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Inject
    ITierService tierService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Account> account=Optional.ofNullable(req.getParameter("id"))
                .map(Integer::parseInt)
                .map(id->accountService.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        account.ifPresent(value -> {
            value.setTier(tierService.findById(value.getTierID()).orElse(new Tier()));
            req.setAttribute("account", value);
        });
        account.orElseThrow(()->new IllegalArgumentException("Can't find relevant content"));
        req.getRequestDispatcher("/page/user/AccountDetail/page.jsp").forward(req, resp);
    }
}
