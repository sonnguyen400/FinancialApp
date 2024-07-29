package com.sonnguyen.individual.nhs.controller.user;


import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import com.sonnguyen.individual.nhs.service.iservice.ILoginCustomerService;
import com.sonnguyen.individual.nhs.type.Message;
import com.sonnguyen.individual.nhs.utils.OTPUtils;
import com.sonnguyen.individual.nhs.utils.RequestUtils;
import com.sonnguyen.individual.nhs.utils.SessionUtils;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "auth",urlPatterns = "/register")
@Model
public class RegisterController extends HttpServlet {
    @Inject
    ILoginCustomerService loginCustomerService;
    @Inject
    ICustomerService customerService;
    @Inject
    OTPUtils otpUtils;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/register.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getRequestURI().equalsIgnoreCase(req.getContextPath()+"/app/otp")){
            Login login= RequestUtils.parseEntity(req,Login.class);
            Customer customer=RequestUtils.parseEntity(req,Customer.class);
            String accountNumber=req.getParameter("accountNumber");
            Account account=new Account();
            account.setAccountNumber(accountNumber);
            customer.setAccounts(List.of(account));
            SessionUtils.setSession(req,"login",login);
            SessionUtils.setSession(req,"customer",customer);
            SessionUtils.setSession(req,"account",account);
            otpUtils.generateOTP()
                    .sessionSave(req)
                    .sendToEmail(req.getParameter("email"));
            SessionUtils.setSession(req,"endpoint",req.getContextPath()+"/register");
            req.getRequestDispatcher(req.getContextPath()+"/app/otp").forward(req,resp);
            return;
        }
        try{
            Login login= (Login) SessionUtils.getSession(req,"login");
            Customer customer=(Customer) SessionUtils.getSession(req,"customer");
            Account account=(Account) SessionUtils.getSession(req,"account");
            loginCustomerService.save(login,customer,account);
            req.setAttribute("message",new Message(Message.Type.SUCCESS,"Register Successfully"));
            doGet(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("message",new Message(Message.Type.ERROR,e.getMessage()));
        }
        doGet(req,resp);
    }
}