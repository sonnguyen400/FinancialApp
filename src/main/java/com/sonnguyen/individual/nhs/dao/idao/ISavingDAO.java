package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.dao.core.GeneralDAO;
import com.sonnguyen.individual.nhs.model.SavingsInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ISavingDAO extends GeneralDAO<SavingsInfo,Integer> {
    Optional<SavingsInfo> findByAccountId(Integer accountId) throws SQLException;
    List<SavingsInfo> findAllMaturity();
    int refreshDate(Connection connection, int savingsId);
}
