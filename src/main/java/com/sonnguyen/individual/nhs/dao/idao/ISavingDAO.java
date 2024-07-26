package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.model.SavingsInfo;

import java.sql.SQLException;
import java.util.Optional;

public interface ISavingDAO extends AbstractDAO<SavingsInfo, Integer> {
    Optional<SavingsInfo> findByAccountId(Integer accountId) throws SQLException;
}
