package com.sonnguyen.individual.nhs;


import com.sonnguyen.individual.nhs.Constant.AccountStatus;
import com.sonnguyen.individual.nhs.Constant.AccountType;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Repository.AccountRepository;
import com.sonnguyen.individual.nhs.Repository.TransactionRepository;

import java.sql.SQLException;
import java.util.List;

public class HelloServlet {
    public static void main(String[] args) throws SQLException {
        TransactionRepository repository=new TransactionRepository();
        List<Transaction> transactions=repository.getHistoryByAccountId(4);
        transactions.forEach(System.out::println);
    }
}