package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.Constant.AccountStatus;
import com.sonnguyen.individual.nhs.Constant.AccountType;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Model.Membership;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;
import com.sonnguyen.individual.nhs.Service.IService.ILoginService;
import com.sonnguyen.individual.nhs.Service.IService.IMembershipService;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;
import com.sonnguyen.individual.nhs.dao.Idao.IMemberShipDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "user-saving-manage-controller",urlPatterns = "/app/saving")
public class SavingsManageController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Inject
    ILoginService loginService;
    @Inject
    IMembershipService membershipService;
    @Inject
    ICustomerService customerService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login= SessionUtils.getPrincipal(req);
        List<Account> accountList=accountService.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.SAVINGS,login.getCustomerId());
        req.setAttribute("accounts",accountList);
        Customer customer=customerService.findById(login.getCustomerId());
        System.out.println(customer.getMembershipID());
        req.setAttribute("membership",membershipService.findById(customer.getMembershipID()).orElse(new Membership()));
        req.getRequestDispatcher("/page/user/SavingAccountManage/page.jsp").forward(req, resp);
    }
}
