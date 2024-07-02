package com.sonnguyen.individual.nhs.controller.User;


import com.sonnguyen.individual.nhs.Constant.MemberShip;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Service.IService.ILoginCustomerService;
import com.sonnguyen.individual.nhs.Utils.RequestUtils;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "auth",urlPatterns = "/register")
@Model
public class RegisterController extends HttpServlet {
    @Inject
    ILoginCustomerService loginCustomerService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/register.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login= RequestUtils.parseEntity(req,Login.class);
        Customer customer=RequestUtils.parseEntity(req,Customer.class);
        customer.setMembership(MemberShip.STANDARD.value);
        String accountNumber=req.getParameter("accountNumber");
        Account account=new Account();
        account.setAccountNumber(accountNumber);
        customer.setAccounts(List.of(account));
        try{
            loginCustomerService.save(login,customer);
        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute(ERROR_MESSAGE,"You cannot register right now !");
            req.getRequestDispatcher("/page/user/register.jsp").forward(req,resp);
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/login");
    }
}
