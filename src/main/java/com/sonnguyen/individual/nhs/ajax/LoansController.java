package com.sonnguyen.individual.nhs.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sonnguyen.individual.nhs.Constant.LoanStatus;
import com.sonnguyen.individual.nhs.Service.IService.ILoanService;
import com.sonnguyen.individual.nhs.Utils.RequestUtils;
import javassist.NotFoundException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "ajax-admin-loans-list-controller",value = "/app/ajax/loans")
public class LoansController extends HttpServlet {
    @Inject
    ILoanService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status=req.getParameter("status");
        ObjectWriter ow=new ObjectMapper().writer().withDefaultPrettyPrinter();
        if(status==null){
            resp.getWriter().write( ow.writeValueAsString(service.findAll()));
        }else if( Arrays.stream(LoanStatus.values()).anyMatch(loanstatus -> Objects.equals(loanstatus.value, status.toUpperCase()))){
            resp.getWriter().write(ow.writeValueAsString(service.findAllByStatus(LoanStatus.valueOf(status.toUpperCase()))));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String,String> parameters=RequestUtils.getParameterMap(req);
        Integer id=Integer.valueOf(parameters.get("id"));
        LoanStatus status=LoanStatus.valueOf(parameters.get("status"));
        try{
            if(status==LoanStatus.APPROVED){
                service.approveLoan(id);
            }else{
                service.updateStatusById(id,status);
            }
        } catch (SQLException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
