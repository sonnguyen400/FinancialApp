package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Repository.IRepository.ITransactionRepository;
import com.sonnguyen.individual.nhs.Service.IService.ITransactionService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

@Model
public class TransactionService implements ITransactionService {
    @Inject
    private ITransactionRepository transactionRepository;
    public Transaction createTransaction(Connection connection,Transaction transaction) throws SQLException {
        Integer id=transactionRepository.createTransaction(connection,transaction);
        transaction.setId(id);
        return transaction;
    }
    public Transaction findById(Integer id) throws SQLException {
        return transactionRepository.findById(id).get();
    }
}
