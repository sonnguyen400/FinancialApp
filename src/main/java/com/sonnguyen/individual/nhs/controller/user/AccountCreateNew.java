package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.dto.Message;
import com.sonnguyen.individual.nhs.dto.Result;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.AccountHolder;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.utils.SessionUtils;
import org.apache.http.HttpStatus;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "/account/new",urlPatterns = "/app/account/new")
public class AccountCreateNew extends HttpServlet {
    @Inject
    private IAccountService accountService;
    @Inject
    private SecurityContextHolder securityContextHolder;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/AccountCreateNew/page.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account= new Account();
        account.setAccountNumber(req.getParameter("accountNumber"));
        try{
            account.setAccountType(Integer.valueOf(req.getParameter("accountType")));
        } catch (Exception ignored) {
        }
        SessionUtils.setSession(req,"newAccount",account);
        if(account.getAccountType()!=null&&account.getAccountType()== AccountType.INCORPORATE.value){
            req.getRequestDispatcher("/page/user/AccountCreateNew/ChooseCoopCustomer/page.jsp").forward(req, resp);
            return;
        }
        Account account1= (Account) SessionUtils.getSession(req,"newAccount");
        List<AccountHolder> holders= new ArrayList<>();
        AccountHolder accountHolder=new AccountHolder();
        accountHolder.setDefault(false);
        accountHolder.setCustomerID(((UserDetailImp)securityContextHolder.getPrincipal()).getCustomerId());
        holders.add(accountHolder);
        String[] coops=req.getParameterMap().get("coop");
        if(coops!=null){
            for(String coop:coops) {
                AccountHolder holder=new AccountHolder(Integer.parseInt(coop));
                holder.setDefault(false);
                holders.add(holder);
            }
        }
        try{
            accountService.createNewAccount(account1,holders);
            req.setAttribute("result",new Result(Message.Type.SUCCESS,"Created new account successfully", HttpStatus.SC_OK));
            req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("result",new Result(Message.Type.ERROR,e.getMessage(),HttpStatus.SC_INTERNAL_SERVER_ERROR));
            req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req, resp);
        }
    }
}
