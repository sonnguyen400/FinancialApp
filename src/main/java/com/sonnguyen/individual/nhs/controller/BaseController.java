package com.sonnguyen.individual.nhs.controller;

import com.sonnguyen.individual.nhs.constant.LoanStatus;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "test",urlPatterns = "/test")
public class BaseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/LoanCreate/page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("LoanCreate")!=null){
            Loan loan= RequestUtils.parseEntity(req, Loan.class);
            loan.setStatus(LoanStatus.PENDING.value);
            System.out.println(loan);
        }
    }
}
