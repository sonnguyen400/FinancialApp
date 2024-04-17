package com.sonnguyen.individual.nhs.WebController.User;


import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Utils.RequestUtils;
import org.springframework.http.HttpStatus;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "auth",urlPatterns = "/app/register")
@Model
public class RegisterController extends HttpServlet {
    @Inject
    private IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/register.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account= RequestUtils.parseEntity(req,Account.class);
        Customer customer=RequestUtils.parseEntity(req,Customer.class);
        try{
            accountService.createNewAccount(account,customer);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("lỗi");
            resp.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            resp.getWriter().println("Fail to create new account");
        }

    }
}
