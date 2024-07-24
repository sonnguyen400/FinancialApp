package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.Service.IService.ITransferService;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

@WebServlet("/app/transfer/commit")
public class TransferCommitController extends HttpServlet {
    @Inject
    ITransferService transferService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!Objects.equals(req.getRequestURI(), "/app/otp")){
            resp.sendRedirect(req.getContextPath()+"/");
            return;
        }
        String refNumber= (String) SessionUtils.getSession(req,"refNumber");
        try{
            req.setAttribute("transfer",transferService.transferCommit(refNumber));
        }catch (Exception e){
            req.setAttribute(ERROR_MESSAGE,e.getMessage());
            req.getRequestDispatcher("/app/transfer").include(req,resp);
            return;
        }
        req.getRequestDispatcher("/page/user/Bill/page.jsp").forward(req,resp);
    }
}
