package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.dao.Idao.ITransactionDAO;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;

@Model
public final class TransactionDAO extends DAO<Transaction,Integer> implements ITransactionDAO {
    @Override
    public Class<Transaction> getEntityClass() {
        return Transaction.class;
    }
    @Override
    public Integer createTransaction(Connection connection, Transaction transaction) throws SQLException {
        return executeInsert(connection, transaction,getEntityClass());
    }
}
