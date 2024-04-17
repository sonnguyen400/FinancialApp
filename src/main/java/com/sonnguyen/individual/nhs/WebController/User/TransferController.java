package com.sonnguyen.individual.nhs.WebController.User;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Model.Transfer;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;
import com.sonnguyen.individual.nhs.Service.IService.ITransferService;
import com.sonnguyen.individual.nhs.Utils.OTPUtils;
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

import static com.sonnguyen.individual.nhs.Utils.Constants.*;
import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "transfer",urlPatterns = "/transfer")
public class TransferController extends HttpServlet {
    @Inject
    ICustomerService customerService;

    @Inject
    OTPUtils otpUtils;
    @Inject
    IAccountService accountService;
    @Inject
    ITransferService transferService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/Transfer/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("account_number")!=null){
            List<Customer> customers= (List<Customer>) customerService.findAllByAccountNumber(req.getParameter("account_number"));
            if(customers.size()>0){
                req.setAttribute("receiver_name",customers.get(0).getFirstname()+customers.get(0).getLastname());
            }else{
                req.setAttribute(ERROR_MESSAGE,"Cant not find receiver information! Reenter account number" );
            }
        }

        Account account= (Account) req.getSession().getAttribute(SessionUtils.LOGIN_SESSION);
        if(req.getParameter(PIN)!=null){
            String pin=accountService.findPINByAccountId(account.getId());
            if(req.getParameter(PIN).equals(pin)){
                req.setAttribute(EXACT_PIN,true);
                creatAndSendOTP(req,"hellohoangson@gmail.com");
            }else{
                req.setAttribute(ERROR_MESSAGE,"Invalid entered PIN");
            }
        }


        if(req.getParameter(OTP)!=null){
            String otp= (String) req.getSession().getAttribute(OTP);
            if(req.getParameter(OTP).equals(otp)){
                req.setAttribute(EXACT_OTP,true);
                Transfer transfer = new Transfer();
                accountService.findAccountByAccountNumber(req.getParameter("account_number")).ifPresent(account_ -> {
                    transfer.setAccountId(account_.getId());
                    transfer.setMessage(req.getParameter("message"));
                });
                Transaction transaction = new Transaction();
                transaction.setAccountId(account.getId());
                transaction.setValue(BigDecimal.valueOf(Double.parseDouble(req.getParameter("amount"))));
                transfer.setTransaction(transaction);
                req.setAttribute("transfer", transferService.startTransfer(transfer));
                req.getRequestDispatcher("/page/user/Bill/page.jsp").forward(req,resp);
                return;
            }else{
                req.setAttribute(EXACT_OTP,false);
                req.setAttribute(ERROR_MESSAGE,"Invalid entered OTP");
            }
        }
        req.getRequestDispatcher("/page/user/Transfer/page.jsp").forward(req,resp);
    }
    public void creatAndSendOTP(HttpServletRequest req,String email){
        otpUtils.generateOTP()
                .sendToEmail(email)
                .sessionSave(req);
    }
}