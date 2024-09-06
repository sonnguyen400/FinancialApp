package com.sonnguyen.individual.nhs.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import org.apache.http.HttpStatus;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "/ajax/get/customers/email",urlPatterns = "/app/ajax/customer/email")
public class CustomerEmailController extends HttpServlet {
    @Inject
    private ICustomerService customerService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectWriter ow=new ObjectMapper().writer()
                .withDefaultPrettyPrinter();
        String email = req.getParameter("value");
        Optional<Customer> customers=customerService.findByEmail(email);
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.setCharacterEncoding("UTF-8");
        if(customers.isEmpty()) resp.setStatus(HttpStatus.SC_NOT_FOUND);
        else {
            resp.setStatus(HttpStatus.SC_OK);
            resp.getWriter().write(ow.writeValueAsString(customers.get()));
        }
    }
}
