package com.sonnguyen.individual.nhs.controller.user;

import com.sonnguyen.individual.nhs.service.iservice.ITransferService;
import com.sonnguyen.individual.nhs.utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE;

@WebServlet(name = "transfer /commit",urlPatterns = "/app/transfer/commit")
public class TransferCommitController extends HttpServlet {
    @Inject
    ITransferService transferService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if( req.getAttribute("OTP")!=null&&req.getAttribute("OTP").equals("VALID")){
            String refNumber= (String) SessionUtils.getSession(req,"refNumber");
            try{
                req.setAttribute("transfer",transferService.transferCommit(refNumber));
            }catch (Exception e){
                req.setAttribute(ERROR_MESSAGE,e.getMessage());
                req.getRequestDispatcher("/app/transfer").include(req,resp);
                return;
            }
            req.getRequestDispatcher("/page/user/Bill/page.jsp").forward(req,resp);
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/");
        return;

    }
}
