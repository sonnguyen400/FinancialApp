package com.sonnguyen.individual.nhs.service.iService;

import com.sonnguyen.individual.nhs.model.Transfer;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransferService {
    Transfer startTransfer(Transfer transfer);
    Transfer startTransfer(Connection connection,Transfer transfer) throws SQLException;
}
