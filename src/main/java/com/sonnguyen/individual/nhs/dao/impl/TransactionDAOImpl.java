package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Transaction;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@Model
public class TransactionDAOImpl extends AbstractDAO<Transaction,Integer> {
    @Override
    protected Class<Transaction> getEntityType() {
        return Transaction.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
    public List<Transaction> findAllByAccountId(Integer accountId)  {
        String query="select * from transaction where account_id=? order by transaction_at desc";
        return executeSelect(query,accountId);
    }
    public List<Transaction> findAllByRefNumber(Connection connection,String refNumber) {
        String query="select * from transaction where reference_number=?";
        return executeSelect(connection,query,refNumber);
    }
    public int updateStatus(Connection connection, Integer transactionId, int status) throws SQLException {
        String query="Update transaction set transaction.status=? where transaction.id=?";
        return executeUpdate(connection,query,status,transactionId);
    }
    public void updateStatusByRefNumber(Connection connection, com.sonnguyen.individual.nhs.constant.TransactionStatus transactionStatus, String refNumber) throws SQLException {
        executeUpdate(connection,"update transaction set transaction.status=? where transaction.reference_number=?",transactionStatus.value,refNumber);
    }
}
