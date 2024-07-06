package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Tier;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ITierService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
@WebServlet(name = "user-account-detail",urlPatterns = "/app/account")
public class AccountDetailController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Inject
    ITierService tierService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Account> account=Optional.ofNullable(req.getParameter("id"))
                .map(Integer::parseInt)
                .map(id->{
                    System.out.println(id);
                    return id;
                })
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
