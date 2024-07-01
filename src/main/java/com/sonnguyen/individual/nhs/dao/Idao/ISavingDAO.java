package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.SavingsInfor;
import com.sonnguyen.individual.nhs.dao.Idao.AbstractDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface ISavingDAO extends AbstractDAO<SavingsInfor, Integer> {
    SavingsInfor findByAccountId(Integer accountId) throws SQLException;
}
