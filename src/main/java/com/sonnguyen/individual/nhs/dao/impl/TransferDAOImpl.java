package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.dao.idao.ITransferDAO;
import com.sonnguyen.individual.nhs.model.Transfer;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
@Model
public class TransferDAOImpl extends AbstractDAO<Transfer,Integer> implements ITransferDAO {
    @Override
    protected Class<Transfer> getEntityType() {
        return Transfer.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
    @Override
    public Optional<Transfer> findByTransactionId(Connection connection,int transactionId) {
        String query="select * from transfer where transaction_id=?";
        List<Transfer> result= executeSelect(connection,query,transactionId);
        if(!result.isEmpty()) return Optional.of(result.get(0));
        return Optional.empty();
    }
}
