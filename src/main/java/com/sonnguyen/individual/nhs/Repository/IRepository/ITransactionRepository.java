package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransactionRepository extends AbstractRepository<Transaction,Integer>{
     Integer createTransaction(Connection connection,Transaction transaction)  throws SQLException;
}
