package com.sonnguyen.individual.nhs.WebController.User;

import com.sonnguyen.individual.nhs.Service.EmailService;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/app/commit")
public class TransferCommitController extends HttpServlet {
    @Inject
    EmailService emailService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/TransferCommit/page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String otp= UUID.randomUUID().toString().substring(0,6);
        try {
            emailService.sendEmail("hellohoangson@gmail.com",otp,"Your OTP");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/page/user/TransferCommit/page.jsp").forward(req, resp);
    }
}
