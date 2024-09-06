package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.constant.TransactionType;
import com.sonnguyen.individual.nhs.model.Transaction;
import com.sonnguyen.individual.nhs.model.Transfer;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ITransferService;
import com.sonnguyen.individual.nhs.utils.RequestUtils;
import com.sonnguyen.individual.nhs.utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "transfer",urlPatterns = "/app/transfer")
public class TransferController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Inject
    ITransferService transferService;
    @Inject
    SecurityContextHolder securityContextHolder;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDetailImp userDetailImp=(UserDetailImp)securityContextHolder.getPrincipal();
        req.setAttribute("accounts",accountService.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.PRIMARY,userDetailImp.getCustomerId()));
        req.getRequestDispatcher("/page/user/Transfer/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getRequestURI().equalsIgnoreCase("/app/transfer")){
            doGet(req,resp);
            return;
        }
        Transfer transfer = RequestUtils.parseEntity(req,Transfer.class);
        Transaction transaction = RequestUtils.parseEntity(req,Transaction.class);

        accountService.findByAccountNumber(req.getParameter("account_number")).ifPresent(account_ -> {
            transfer.setAccountId(account_.getId());
        });
        transaction.setTransactionType(TransactionType.TRANSFER.value);
        transfer.setTransaction(transaction);
        String transactionRefNumber=transferService.init(transfer);

        SessionUtils.setSession(req,"refNumber",transactionRefNumber);
        SessionUtils.setSession(req,"endpoint",req.getContextPath()+"/app/transfer/commit");
        req.getRequestDispatcher("/app/pin").include(req,resp);
    }

}
