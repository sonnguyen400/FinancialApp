package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Repository.IRepository.ITransactionRepository;
import com.sonnguyen.individual.nhs.Service.IService.ITransactionService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

@Model
public class TransactionLogService implements ITransactionService {
    @Inject
    private ITransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Connection connection, Transaction transaction) throws SQLException {
        return null;
    }
}
