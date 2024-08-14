package com.sonnguyen.individual.nhs.controller.admin;

import com.sonnguyen.individual.nhs.dto.Alert;
import com.sonnguyen.individual.nhs.dto.Message;
import com.sonnguyen.individual.nhs.service.iservice.ILoanService;
import javassist.NotFoundException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "admin-loans-manage",urlPatterns = "/admin/loan-manage")
public class LoanManage extends HttpServlet {
    @Inject
    ILoanService loanService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/admin/LoanManage/page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            if(req.getParameter("approve") != null) {
                loanService.approveLoan(Integer.valueOf(req.getParameter("approve")));
            }
            req.setAttribute("alert",new Alert(Message.Type.SUCCESS,"Success"));
            doGet(req, resp);
        } catch (SQLException | NotFoundException e) {
            req.setAttribute("alert",new Alert(Message.Type.ERROR,"Something went wrong"));
            doGet(req, resp);
        }
    }
}
