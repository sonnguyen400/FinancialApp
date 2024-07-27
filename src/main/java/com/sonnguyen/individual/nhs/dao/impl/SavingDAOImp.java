package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.SavingsInfo;

import javax.enterprise.inject.Model;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Model
public class SavingDAOImp extends AbstractDAO<SavingsInfo,Integer> {
    @Override
    protected Class<SavingsInfo> getEntityType() {
        return SavingsInfo.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
    public Optional<SavingsInfo> findByAccountId(Integer accountId) throws SQLException {
        String query = "select * from savings_info where account_id=?";
        List<SavingsInfo> result=executeSelect(query,accountId);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }
}
