package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.model.SavingsInfor;

import java.sql.SQLException;

public interface ISavingDAO extends AbstractDAO<SavingsInfor, Integer> {
    SavingsInfor findByAccountId(Integer accountId) throws SQLException;
}
