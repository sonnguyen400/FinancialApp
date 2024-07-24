package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ITransactionService {
    Transaction createTransaction(Connection connection,Transaction transaction) throws SQLException;
    List<Transaction> findHistoryByAccountId(Integer accountId);
    List<Transaction> findAllByRefNumber(String refNumber);
}
