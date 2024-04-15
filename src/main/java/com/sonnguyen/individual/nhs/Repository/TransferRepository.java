package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Transfer;
import com.sonnguyen.individual.nhs.Repository.IRepository.ITransferRepository;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
@Model
public class TransferRepository extends Repository<Transfer,Integer> implements ITransferRepository {

    @Override
    public Class<Transfer> getEntityClass() {
        return Transfer.class;
    }

    @Override
    public Transfer startTransfer(Connection connection,Transfer transfer) throws SQLException {
            transfer.setId(executeInsert(connection,transfer,getEntityClass()));
            return transfer;
    }

}
