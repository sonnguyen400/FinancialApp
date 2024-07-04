package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.dao.Idao.ITransactionDAO;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
    @Override
    public List<Transaction> findAllByAccountId(Integer accountId)  {
        String query="select * from transaction where account_id=? order by transaction_at desc";
        try {
            return executeSelect(query,accountId,accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> findAllByRefNumber(String refNumber) {
        String query="select * from transaction where reference_number=?";
        try {
            return executeSelect(query,refNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateStatus(Connection connection, Integer transactionId, int status) throws SQLException {
        String query="Update transaction set transaction.status=? where transaction.id=?";
        return executeUpdate(connection,query,status,transactionId);
    }

}
