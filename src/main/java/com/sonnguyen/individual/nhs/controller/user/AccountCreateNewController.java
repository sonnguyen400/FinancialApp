package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.dto.Message;
import com.sonnguyen.individual.nhs.dto.Result;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.AccountHolder;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.utils.RequestUtils;
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
public class AccountCreateNewController extends HttpServlet {
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
        Account account= RequestUtils.parseEntity(req, Account.class);
        if(account.getAccountType()!=null&&account.getAccountType()==AccountType.INCORPORATE.value){
            SessionUtils.setSession(req,"account",account);
            req.getRequestDispatcher("/page/user/AccountCreateNew/ChooseCoopCustomer/page.jsp").forward(req, resp);
            return;
        }
        if(account.getAccountType()!=null&&account.getAccountType()==AccountType.PRIMARY.value){
            AccountHolder accountHolder=new AccountHolder(((UserDetailImp)securityContextHolder.getPrincipal()).getCustomerId());
            accountHolder.setDefault(false);
            accountService.createNewAccount(account,List.of(accountHolder));
            req.setAttribute("result",new Result(Message.Type.SUCCESS,"Created new account successfully", HttpStatus.SC_OK));
            req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req, resp);
            return;
        }
        if(req.getParameter("action").equals("addmember")){
            AccountHolder accountHolder=new AccountHolder(((UserDetailImp)securityContextHolder.getPrincipal()).getCustomerId());
            accountHolder.setDefault(false);
            List<AccountHolder> holders=new ArrayList<>();
            holders.add(accountHolder);
            String[] memberId=req.getParameterMap().get("memberId");
            if(memberId!=null){
                for(String coop:memberId) {
                    AccountHolder holder=new AccountHolder(Integer.parseInt(coop));
                    holder.setDefault(false);
                    holders.add(holder);
                }
            }
            Account account1= (Account) SessionUtils.getSession(req,"account");
            accountService.createNewAccount(account1,holders);
            req.setAttribute("result",new Result(Message.Type.SUCCESS,"Created new account successfully", HttpStatus.SC_OK));
            req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("result",new Result(Message.Type.ERROR,"Created new account failure", HttpStatus.SC_OK));
        req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req, resp);

    }
}
