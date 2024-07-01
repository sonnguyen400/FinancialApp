package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.dao.Idao.ITransactionDAO;
import com.sonnguyen.individual.nhs.Service.IService.ITransactionService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Model
public class TransactionService implements ITransactionService {
    @Inject
    private ITransactionDAO transactionRepository;
    public Transaction createTransaction(Connection connection,Transaction transaction) throws SQLException {
        Integer id=transactionRepository.createTransaction(connection,transaction);
        transaction.setId(id);
        return transaction;
    }
    @Override
    public List<Transaction> findHistoryByAccountId(Integer accountId){
        try {
            return transactionRepository.getHistoryByAccountId(accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Transaction findById(Integer id) throws SQLException {
        return transactionRepository.findById(id).get();
    }
}
