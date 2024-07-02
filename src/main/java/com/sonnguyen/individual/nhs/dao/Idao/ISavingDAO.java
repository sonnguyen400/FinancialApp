package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.SavingsInfo;

import java.sql.SQLException;

public interface ISavingDAO extends AbstractDAO<SavingsInfo, Integer> {
    SavingsInfo findByAccountId(Integer accountId) throws SQLException;
}
