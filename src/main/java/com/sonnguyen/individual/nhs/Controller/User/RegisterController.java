package com.sonnguyen.individual.nhs.Controller.User;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "auth",urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/user/register.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> param=req.getParameterMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,object->{
                    if(object.getValue().length==1) return object.getValue()[0];
                    else if(object.getValue().length==0) return null;
                    return object.getValue();
                }));
        ObjectMapper objectMapper=new ObjectMapper();
        Account account=objectMapper.convertValue(param,Account.class);
        Customer customer=objectMapper.convertValue(param, Customer.class);


    }
}
