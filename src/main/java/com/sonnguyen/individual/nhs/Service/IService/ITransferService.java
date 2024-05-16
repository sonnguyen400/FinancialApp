package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Model.Transfer;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransferService {
    Transfer startTransfer(Transfer transfer);
    Transfer startTransfer(Connection connection,Transfer transfer) throws SQLException;
}
