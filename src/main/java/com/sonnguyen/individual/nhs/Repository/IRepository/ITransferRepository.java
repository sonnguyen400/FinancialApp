package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Transfer;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransferRepository extends AbstractRepository<Transfer,Integer>{
    Transfer startTransfer(Connection connection,Transfer transfer) throws SQLException;

}
