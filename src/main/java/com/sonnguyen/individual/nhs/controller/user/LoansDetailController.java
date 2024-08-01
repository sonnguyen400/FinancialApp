package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.dto.Alert;
import com.sonnguyen.individual.nhs.dto.Message;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.service.PaymentService;
import com.sonnguyen.individual.nhs.service.iservice.ILoanService;
import org.apache.velocity.exception.ResourceNotFoundException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loan/detail",urlPatterns = "/app/loan/detail")
public class LoansDetailController extends HttpServlet {
    @Inject
    ILoanService loanService;
    @Inject
    PaymentService paymentService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int loanId = Integer.parseInt(req.getParameter("id"));
        Loan loan=loanService.findById(loanId).orElseThrow(()->new ResourceNotFoundException("Loan with id "+loanId+" not found"));
        req.setAttribute("loan", loan);
        int unpaidMonth= paymentService.unpaidMonth(loanId);
        if(unpaidMonth==1){
            req.setAttribute("alert", new Alert(Message.Type.WARNING,"Payment in this month has yet made",req.getContextPath()+"/app/loan/payment?id="+loan.getId(),"Continue"));
        }else if(unpaidMonth>1){
            req.setAttribute("alert", new Alert(Message.Type.ERROR,"we have not received your monthly loan payment for "+unpaidMonth+" months",req.getContextPath()+"/app/loan/payment?id="+loan.getId(),"Continue"));
        }
        req.getRequestDispatcher("/page/user/LoanDetail/page.jsp").forward(req, resp);
    }
}
