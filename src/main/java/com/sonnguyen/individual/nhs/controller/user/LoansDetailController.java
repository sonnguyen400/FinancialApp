package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.constant.LoanStatus;
import com.sonnguyen.individual.nhs.dto.Alert;
import com.sonnguyen.individual.nhs.dto.Message;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.service.iservice.ILoanService;
import com.sonnguyen.individual.nhs.service.iservice.IPaymentService;
import org.apache.velocity.exception.ResourceNotFoundException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loan/detail",urlPatterns = "/app/loan/detail")
public class LoansDetailController extends HttpServlet {
    @Inject
    ILoanService loanService;
    @Inject
    IPaymentService paymentService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int loanId = Integer.parseInt(req.getParameter("id"));
        Loan loan=loanService.findById(loanId).orElseThrow(()->new ResourceNotFoundException("Loan with id "+loanId+" not found"));
        req.setAttribute("loan", loan);
        if(loan.getStatus()== LoanStatus.APPROVED.value){
            Date paymentdate=paymentService.findNextPaymentByLoanId(loanId);
            LocalDateTime now=LocalDateTime.now();
            LocalDateTime paymentDate=paymentdate.toLocalDate().atTime(LocalTime.MAX);
            long diff = ChronoUnit.DAYS.between(now,paymentDate);
            int unpaid=paymentService.unpaidMonth(loanId);

            List<Alert> alerts=new ArrayList<>();
            if(diff<=3&&diff!=0){
                alerts.add( new Alert(Message.Type.WARNING,"Your loan monthly payment are coming up"));
            }
            if(diff==0){
                alerts.add(new Alert(Message.Type.INFO,"Today is monthly payment day",req.getContextPath()+"/app/loan/payment?id="+loan.getId(),"Continue"));
            }
            if(unpaid>=2){
                alerts.add(new Alert(Message.Type.ERROR,"Your payment has late for "+(unpaid-1)+" months"));
            }
            req.setAttribute("alerts", alerts);
        }
        req.getRequestDispatcher("/page/user/LoanDetail/page.jsp").forward(req, resp);
    }
}
