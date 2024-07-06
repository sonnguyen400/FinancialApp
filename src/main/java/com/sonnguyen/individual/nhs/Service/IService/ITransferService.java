package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Exception.FailureTransaction;
import com.sonnguyen.individual.nhs.Model.Transfer;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransferService {
    Transfer startTransfer(Connection connection,Transfer transfer) throws SQLException;
    String init(Connection connection,Transfer transfer) throws SQLException;
    String init(Transfer transfer);
    Transfer transferCommit(Connection connection,String transactionRefNumber) throws SQLException;
    Transfer transferCommit(String transactionRefNumber) throws FailureTransaction;
}
