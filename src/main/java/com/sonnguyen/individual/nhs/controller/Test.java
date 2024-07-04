package com.sonnguyen.individual.nhs.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

class InnerApp {
    @NotNull
    public Integer a;
    public int b;
    public Long c;
    public InnerApp(){

    }
}
@WebServlet("/testnew")
public class Test extends HttpServlet {
    static final Logger logger= LogManager.getLogger(Test.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/Result/page.jsp").forward(req,resp);
    }
}
