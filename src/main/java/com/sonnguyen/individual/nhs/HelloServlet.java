package com.sonnguyen.individual.nhs;


import com.sonnguyen.individual.nhs.model.Transaction;
import com.sonnguyen.individual.nhs.dao.TransactionDAO;

import java.sql.SQLException;
import java.util.List;

public class HelloServlet {
    public static void main(String[] args) throws SQLException {
        TransactionDAO repository=new TransactionDAO();
        List<Transaction> transactions=repository.getHistoryByAccountId(4);
        transactions.forEach(System.out::println);
    }
}