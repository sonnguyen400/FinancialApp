package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.dto.Message;
import com.sonnguyen.individual.nhs.dto.Result;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Tier;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ITierService;
import com.sonnguyen.individual.nhs.utils.SessionUtils;
import org.apache.http.HttpStatus;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getRequestURI().equals(req.getContextPath()+"/app/otp")){
            SessionUtils.setSession(req,"endpoint",req.getContextPath()+"/app/account");
            if(req.getParameter("freeze") != null) {
                SessionUtils.setSession(req,"freeze",Integer.parseInt(req.getParameter("freeze")));
            }
            if(req.getParameter("inactive") != null) {
                SessionUtils.setSession(req,"inactive",Integer.parseInt(req.getParameter("inactive")));
            }
            if(req.getParameter("unfreeze") != null) {
                SessionUtils.setSession(req,"unfreeze",Integer.parseInt(req.getParameter("unfreeze")));
            }
            resp.sendRedirect(req.getContextPath()+"/app/pin");
            return;
        }
        if(SessionUtils.getSession(req,"freeze") != null) {
            accountService.freezeAccount((Integer) SessionUtils.getSession(req,"freeze"));
        }
        if(SessionUtils.getSession(req,"inactive") != null) {
            accountService.inactiveAccount((Integer) SessionUtils.getSession(req,"inactive"));
        }
        if(SessionUtils.getSession(req,"unfreeze") != null) {
            accountService.openAccount((Integer) SessionUtils.getSession(req,"unfreeze"));
        }
        req.setAttribute("result",new Result(Message.Type.SUCCESS,"Success",HttpStatus.SC_OK));
        req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req, resp);
    }
}
