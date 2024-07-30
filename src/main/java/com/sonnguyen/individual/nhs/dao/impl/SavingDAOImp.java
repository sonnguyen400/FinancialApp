package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.idao.ISavingDAO;
import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.model.SavingsInfo;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Model
public class SavingDAOImp extends AbstractDAO<SavingsInfo,Integer> implements ISavingDAO {
    @Override
    protected Class<SavingsInfo> getEntityType() {
        return SavingsInfo.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
    @Override
    public Optional<SavingsInfo> findByAccountId(Integer accountId) throws SQLException {
        String query = "select * from savings_info where account_id=?";
        List<SavingsInfo> result=executeSelect(query,accountId);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }
    @Override
    public int refreshDate(Connection connection, int savingsId) {
        String query = "update savings_info set update_at=now() where savings_id=?";
        return executeUpdate(connection,query,savingsId);
    }
    @Override
    public List<SavingsInfo> findAllMaturity() {
        String query = "select * from savings_info\n" +
                "         where DATE_ADD(update_at, INTERVAL term MONTH )<now()\n";
        return executeSelect(query);
    }
}
