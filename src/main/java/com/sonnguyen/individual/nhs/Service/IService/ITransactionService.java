package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransactionService {
    public Transaction createTransaction(Connection connection,Transaction transaction) throws SQLException;
}
