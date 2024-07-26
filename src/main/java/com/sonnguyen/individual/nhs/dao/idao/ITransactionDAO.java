package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.Constant.TransactionStatus;
import com.sonnguyen.individual.nhs.model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ITransactionDAO extends AbstractDAO<Transaction,Integer> {
     Integer createTransaction(Connection connection,Transaction transaction)  throws SQLException;
     List<Transaction> findAllByAccountId(Integer accountId);
     List<Transaction> findAllByRefNumber(String refNumber);
     int updateStatus(Connection connection,Integer transactionId, int status) throws SQLException;
     void updateStatusByRefNumber(Connection connection, TransactionStatus transactionStatus, String refNumber) throws SQLException;
}
