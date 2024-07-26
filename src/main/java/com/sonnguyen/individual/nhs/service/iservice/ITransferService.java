package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.exception.FailureTransaction;
import com.sonnguyen.individual.nhs.model.Transfer;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransferService {
    Transfer startTransfer(Connection connection,Transfer transfer) throws SQLException;
    String init(Connection connection,Transfer transfer) throws SQLException;
    String init(Transfer transfer);
    Transfer transferCommit(Connection connection,String transactionRefNumber) throws SQLException;
    Transfer transferCommit(String transactionRefNumber) throws FailureTransaction;
}
