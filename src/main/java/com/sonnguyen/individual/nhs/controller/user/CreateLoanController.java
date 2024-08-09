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
import com.sonnguyen.individual.nhs.service.iservice.*;
import com.sonnguyen.individual.nhs.utils.RequestUtils;
import com.sonnguyen.individual.nhs.utils.SessionUtils;
import org.apache.velocity.exception.ResourceNotFoundException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

@WebServlet(name = "loan/create", urlPatterns = "/app/loan/create")
public class CreateLoanController extends HttpServlet {
    @Inject
    private ICustomerService customerService;
    @Inject
    private IAccountService accountService;
    @Inject
    private ILoanService loanService;
    @Inject
    private IMembershipService membershipService;
    @Inject
    private SecurityContextHolder securityContextHolder;
    @Inject
    private ILoanSettingService loanSettingService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDetailImp userDetailImp= (UserDetailImp) securityContextHolder.getPrincipal();
        req.setAttribute("accounts", accountService.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.PRIMARY, userDetailImp.getCustomer().getId()));
        Customer customer = customerService.findById(userDetailImp.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("user not found"));
        req.setAttribute("loanSettings", loanSettingService.findAll());
        req.setAttribute("membership", membershipService.findById(customer.getMembershipID()).orElse(new Membership()));
        req.getRequestDispatcher("/page/user/LoanCreate/page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getRequestURI().equalsIgnoreCase(req.getContextPath()+"/app/otp")) {
            SessionUtils.setSession(req,"loan",RequestUtils.parseEntity(req, Loan.class));
            req.getSession().setAttribute("endpoint", "/app/loan/create");
            resp.sendRedirect(req.getContextPath()+"/app/pin");
            return;
        }
        UserDetailImp userDetailImp= (UserDetailImp) securityContextHolder.getPrincipal();
        Loan loan = (Loan) SessionUtils.getSession(req,"loan");
        loanSettingService.findById(loan.getTerm_id()).ifPresentOrElse(loanSetting -> {
            loan.setInterestRate(BigDecimal.valueOf(loanSetting.getInterestRate()));
            loan.setTerm(loanSetting.getTerm());
        },()->{
            throw new IllegalArgumentException("Setting not found");
        });
        loan.setCustomerId(userDetailImp.getCustomerId());
        loan.setBranchId(DefaultBrand.ID.value);
        loanService.save(loan);
        req.setAttribute("result",new Result(Message.Type.SUCCESS,"Your loan is in processing! Please pantient until received formal email",1));
        req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req, resp);
    }
}
