package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.constant.TransactionType;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.model.Transaction;
import com.sonnguyen.individual.nhs.model.Transfer;
import com.sonnguyen.individual.nhs.service.iService.*;
import com.sonnguyen.individual.nhs.Utils.OTPUtils;
import com.sonnguyen.individual.nhs.Utils.RequestUtils;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import static com.sonnguyen.individual.nhs.constant.RequestFlags.CREATE_TRANSFER;
import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

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
        if(req.getParameter(CREATE_TRANSFER.value)!=null&&req.getParameter("receiver_name")!=""){
            Transfer transfer = RequestUtils.parseEntity(req,Transfer.class);
            Transaction transaction = RequestUtils.parseEntity(req,Transaction.class);
            accountService.findAccountByAccountNumber(req.getParameter("account_number")).ifPresent(account_ -> {
                transfer.setAccountId(account_.getId());
            });
            transaction.setTransactionType(TransactionType.TRANSFER.value);
            transaction.setValue(BigDecimal.valueOf(Double.parseDouble(req.getParameter("amount"))));
            transfer.setTransaction(transaction);
            SessionUtils.setSession(req,"transfer",transfer);
            SessionUtils.setSession(req,"endpoint","/app/transfer");
            req.getRequestDispatcher("/pin").forward(req,resp);
            return;
        }
        if((boolean)req.getAttribute("validOTP")){
            Transfer transfer=transferService.startTransfer((Transfer) SessionUtils.getSession(req,"transfer"));
            if (transfer == null) {
                req.setAttribute(ERROR_MESSAGE,"Error");
                req.getRequestDispatcher("/page/user/Transfer/page.jsp").forward(req,resp);
                return;
            }
            req.setAttribute("transfer", transfer);
            req.getRequestDispatcher("/page/user/Bill/page.jsp").forward(req,resp);
        }
    }

}
