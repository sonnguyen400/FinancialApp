package com.sonnguyen.individual.nhs.WebController.User;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Model.SavingsInfor;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Utils.RequestUtils;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "create-savings",urlPatterns = "/app/saving/create")
@Model
public class CreateSavingsController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login= SessionUtils.getPrincipal(req);
        req.setAttribute("accounts",accountService.findAllByCustomerId(login.getCustomerId()));
        req.getRequestDispatcher("/page/user/SavingAccountCreate/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter(RequestUtils.Flags.CREATE_SAVINGS.value)!=null){
            Login login= SessionUtils.getPrincipal(req);
            SavingsInfor savingsInfor=RequestUtils.parseEntity(req,SavingsInfor.class);
            try{
                accountService.createSavingsAccount(login.getCustomerId(), savingsInfor);
                resp.sendRedirect(req.getContextPath() + "/app/saving");
            }catch (Exception e){
                req.setAttribute("accounts",accountService.findAllByCustomerId(login.getCustomerId()));
                e.printStackTrace();
                req.setAttribute(RequestUtils.ERROR_MESSAGE,"Error while create saving");
                req.getRequestDispatcher("/page/user/SavingAccountCreate/page.jsp").forward(req,resp);

            }

        }
    }
}
