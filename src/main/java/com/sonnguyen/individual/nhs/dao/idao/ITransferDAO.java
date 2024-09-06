package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.dao.core.GeneralDAO;
import com.sonnguyen.individual.nhs.model.Transfer;

import java.sql.Connection;
import java.util.Optional;

public interface ITransferDAO extends GeneralDAO<Transfer,Integer> {
    Optional<Transfer> findByTransactionId(Connection connection, int transactionId);
}
