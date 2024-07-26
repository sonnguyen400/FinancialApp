package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.model.Transfer;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransferDAO extends AbstractDAO<Transfer,Integer> {
    Transfer startTransfer(Connection connection,Transfer transfer) throws SQLException;
    Transfer findByTransactionId(Connection connection,Integer transactionId) throws SQLException;
}
