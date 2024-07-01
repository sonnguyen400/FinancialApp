package com.sonnguyen.individual.nhs.service.iService;

import com.sonnguyen.individual.nhs.model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ITransactionService {
    public Transaction createTransaction(Connection connection,Transaction transaction) throws SQLException;
    List<Transaction> findHistoryByAccountId(Integer accountId);
}
