package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransactionDAO extends AbstractDAO<Transaction,Integer> {
     Integer createTransaction(Connection connection,Transaction transaction)  throws SQLException;
}
