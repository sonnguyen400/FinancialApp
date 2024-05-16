package com.sonnguyen.individual.nhs.AjaxController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ajax-account-number-controller",value = "/app/ajax/accountNumber")
public class AccountNumberController extends HttpServlet {
    @Inject
    ICustomerService customerService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers= (List<Customer>) customerService.findAllByAccountNumber(req.getParameter("accountNumber"));
        ObjectWriter ow=new ObjectMapper().writer().withDefaultPrettyPrinter();
        resp.getWriter().print(ow.writeValueAsString(customers.get(0)));
    }
}
