package com.sonnguyen.individual.nhs;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Model.Transfer;
import com.sonnguyen.individual.nhs.Repository.GeneralRepository;
import com.sonnguyen.individual.nhs.Service.TransferService;
import com.sonnguyen.individual.nhs.Utils.MemberShip;
import com.sonnguyen.individual.nhs.Utils.Route;

import java.math.BigDecimal;
import java.sql.Connection;

public class HelloServlet {
    public static void main(String[] args){
//        Connection connection=GeneralRepository.getConnection();
//        TransferService transferService=new TransferService();
//        Transfer transfer=new Transfer();
//        transfer.setAccountId(4);
//        Transaction transaction=new Transaction();
//        transaction.setValue(BigDecimal.valueOf(5000000));
//        transaction.setAccountId(7);
//        transfer.setTransaction(transaction);
//        transferService.startTransfer(transfer);
        System.out.println(Route.LOAN_CREATE.name());
    }
}