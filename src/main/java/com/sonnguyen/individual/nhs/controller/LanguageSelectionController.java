package com.sonnguyen.individual.nhs.controller;

import com.sonnguyen.individual.nhs.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "language",urlPatterns = "/language")
public class LanguageSelectionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("lang") != null) SessionUtils.setSession(req,"lang",req.getParameter("lang"));
        resp.sendRedirect(req.getContextPath()+req.getParameter("language_page_uri"));
    }
}
