package com.sonnguyen.individual.nhs.WebController.User;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "transfer",urlPatterns = "/transfer")
public class Transfer extends HttpServlet {
    @Inject
    ICustomerService customerService;
    @Inject
    IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Account account= (Account) SessionUtils.getSession(req,SessionUtils.LOGIN_SESSION);
//        try {
//            if(account!=null){
//                req.setAttribute("customers",customerService.findAllByAccountId(account.getId()));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        req.getRequestDispatcher("/page/user/Transfer/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Customer> customers= (List<Customer>) customerService.findAllByAccountNumber(req.getParameter("account_number"));
            if(customers==null||customers.size()==0){
                req.setAttribute(ERROR_MESSAGE,"Cant not find receiver information! Reenter account number" );
            }else{
                req.setAttribute("receiver_name",customers.get(0).getEmail());
            }

        } catch (SQLException e) {
            req.setAttribute(ERROR_MESSAGE,"Cant not find receiver information! Reenter account number" );
        }
        req.getRequestDispatcher("/page/user/Transfer/page.jsp").forward(req,resp);
    }
}
