package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.Constant.TransactionType;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Model.Transfer;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ITransferService;
import com.sonnguyen.individual.nhs.Utils.RequestUtils;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer=SessionUtils.getPrincipal(req).getCustomer();
        req.setAttribute("accounts",accountService.findAllByCustomerId(customer.getId()));
        req.getRequestDispatcher("/page/user/Transfer/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login=SessionUtils.getPrincipal(req);

        Transfer transfer = RequestUtils.parseEntity(req,Transfer.class);
        Transaction transaction = RequestUtils.parseEntity(req,Transaction.class);

        accountService.findAccountByAccountNumber(req.getParameter("account_number")).ifPresent(account_ -> {
            transfer.setAccountId(account_.getId());
        });
        transaction.setTransactionType(TransactionType.TRANSFER.value);
        transfer.setTransaction(transaction);

        String transactionRefNumber=transferService.init(transfer);

        SessionUtils.setSession(req,"refNumber",transactionRefNumber);
        SessionUtils.setSession(req,"endpoint","/app/transfer/commit");
        req.getRequestDispatcher("/app/pin").include(req,resp);
    }

}
