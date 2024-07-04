package com.sonnguyen.individual.nhs.controller.User;

import com.sonnguyen.individual.nhs.Model.SavingsInfo;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ISavingsInfoService;
import com.sonnguyen.individual.nhs.dao.Idao.ISavingDAO;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/app/saving/account")
public class SavingDetailController extends HttpServlet {
    @Inject
    IAccountService accountService;
    @Inject
    ISavingsInfoService savingsInfoService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<SavingsInfo> savingsInfo= Optional.ofNullable(req.getParameter("id"))
                .map(Integer::parseInt).flatMap(id -> accountService.findById(id))
                .map(account ->
                        savingsInfoService.findByAccountId(account.getId()).map(savingsInfo1 -> {
                            savingsInfo1.setAccount(account);
                            return savingsInfo1;
                })
                                .orElseGet(null));
        if(savingsInfo.isPresent()){
            System.out.println(savingsInfo);
            req.setAttribute("savings",savingsInfo.get());
            req.getRequestDispatcher("/page/user/SavingAccountDetail/page.jsp").forward(req,resp);
        }else {
            resp.sendError(HttpStatus.NOT_FOUND.value(),"Saving account not found");
        }

    }



}
