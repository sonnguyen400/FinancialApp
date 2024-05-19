package com.sonnguyen.individual.nhs.WebController.User;

import com.sonnguyen.individual.nhs.Constant.TransactionType;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Model.Transfer;
import com.sonnguyen.individual.nhs.Service.IService.*;
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

import static com.sonnguyen.individual.nhs.Constant.RequestFlags.CREATE_TRANSFER;
import static com.sonnguyen.individual.nhs.Utils.Constants.*;
import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "transfer",urlPatterns = "/app/transfer")
public class TransferController extends HttpServlet {
    @Inject
    ICustomerService customerService;
    @Inject
    ILoginCustomerService loginCustomerService;
    @Inject
    ILoginService loginService;
    @Inject
    OTPUtils otpUtils;
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
            req.getRequestDispatcher("/page/user/EnterPin/page.jsp").forward(req,resp);
            return;
        }
        //Nhập pin và chuyển sang bước nhập otp
        if(req.getParameter(PIN)!=null){

            if(loginService.validatePIN(login.getId(),req.getParameter(PIN))){
                req.setAttribute(EXACT_PIN,true);
                otpUtils.generateOTP().sendToEmail("hellohoangson@gmail.com").sessionSave(req);
                req.getRequestDispatcher("/page/user/EnterOTP/page.jsp").forward(req,resp);
            }else{
                req.setAttribute(ERROR_MESSAGE,"Invalid entered PIN");
                req.getRequestDispatcher("/page/user/EnterPin/page.jsp").forward(req,resp);
            }
        }


        if(req.getParameter(OTP)!=null){
            String otp= (String) SessionUtils.getSession(req,OTP);
            if(req.getParameter(OTP).equals(otp)){
                Transfer transfer=transferService.startTransfer((Transfer) SessionUtils.getSession(req,"transfer"));
                if (transfer == null) {
                    req.setAttribute(ERROR_MESSAGE,"Error");
                    req.getRequestDispatcher("/page/user/Transfer/page.jsp").forward(req,resp);
                    return;
                }
                req.setAttribute("transfer", transfer);
                req.getRequestDispatcher("/page/user/Bill/page.jsp").forward(req,resp);
            }else{
                req.setAttribute(ERROR_MESSAGE,"Invalid entered OTP");
                req.getRequestDispatcher("/page/user/EnterOTP/page.jsp").forward(req,resp);
            }
        }
    }

}
