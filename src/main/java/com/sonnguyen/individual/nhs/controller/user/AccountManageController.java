package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "account/manage",urlPatterns = "/app/account/manage")
public class AccountManageController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Inject
    SecurityContextHolder securityContextHolder;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDetailImp userDetailImp=(UserDetailImp)securityContextHolder.getPrincipal();
        List<Account> accounts=accountService.findByCustomerIdAndType(AccountType.PRIMARY,userDetailImp.getCustomerId());
        List<Account> coopAccounts=accountService.findByCustomerIdAndType(AccountType.INCORPORATE,userDetailImp.getCustomerId());
        req.setAttribute("accounts",accounts);
        req.setAttribute("coopAccounts",coopAccounts);
        req.setAttribute("sum",accounts.stream().reduce(BigDecimal.ZERO,(pre,acc)->acc.getBalance().add(pre),BigDecimal::add));
        req.getRequestDispatcher("/page/user/AccountManage/page.jsp").forward(req,resp);
    }
}
