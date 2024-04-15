package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Repository.IRepository.ITransactionRepository;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;

@Model
public final class TransactionRepository extends Repository<Transaction,Integer> implements ITransactionRepository {
    @Override
    public Class<Transaction> getEntityClass() {
        return Transaction.class;
    }
    @Override
    public Integer createTransaction(Connection connection, Transaction transaction) throws SQLException {
        return executeInsert(connection, transaction,getEntityClass());
    }
}
