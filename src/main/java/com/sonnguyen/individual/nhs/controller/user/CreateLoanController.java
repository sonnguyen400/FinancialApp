package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.constant.DefaultBrand;
import com.sonnguyen.individual.nhs.dto.Message;
import com.sonnguyen.individual.nhs.dto.Result;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.model.Membership;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import com.sonnguyen.individual.nhs.service.iservice.ILoanService;
import com.sonnguyen.individual.nhs.service.iservice.IMembershipService;
import com.sonnguyen.individual.nhs.utils.RequestUtils;
import com.sonnguyen.individual.nhs.utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "loan/create", urlPatterns = "/app/loan/create")
public class CreateLoanController extends HttpServlet {
    @Inject
    ICustomerService customerService;
    @Inject
    IAccountService accountService;
    @Inject
    ILoanService loanService;
    @Inject
    IMembershipService membershipService;
    @Inject
    SecurityContextHolder securityContextHolder;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURI());
        if (req.getRequestURI().equalsIgnoreCase(req.getContextPath()+"/app/otp")) {
            UserDetailImp userDetailImp= (UserDetailImp) securityContextHolder.getPrincipal();
            req.setAttribute("accounts", accountService.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.PRIMARY, userDetailImp.getCustomer().getId()));
            Customer customer = customerService.findById(userDetailImp.getCustomerId()).get();
            req.setAttribute("membership", membershipService.findById(customer.getMembershipID()).orElse(new Membership()));
            req.getRequestDispatcher("/page/user/LoanCreate/page.jsp").forward(req, resp);
            return;
        }
        req.getSession().setAttribute("endpoint", "/app/loan/create");
        resp.sendRedirect(req.getContextPath()+"/app/pin");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getRequestURI().equals(req.getContextPath()+"/app/otp")) {
            UserDetailImp userDetailImp= (UserDetailImp) securityContextHolder.getPrincipal();
            Loan loan = RequestUtils.parseEntity(req, Loan.class);
            loan.setCustomerId(userDetailImp.getCustomerId());
            loan.setBranchId(DefaultBrand.ID.value);
            loanService.save(loan);
            req.setAttribute("result",new Result(Message.Type.SUCCESS,"Your loan is in processing! Please pantient until received formal email",1));
            req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req, resp);
        } else {
            doGet(req,resp);
        }
    }
}
