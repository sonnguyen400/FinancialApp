package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ITransactionService {
    Transaction createTransaction(Connection connection,Transaction transaction) throws SQLException;
    List<Transaction> findHistoryByAccountId(Integer accountId);
    List<Transaction> findAllByRefNumber(String refNumber);
}
