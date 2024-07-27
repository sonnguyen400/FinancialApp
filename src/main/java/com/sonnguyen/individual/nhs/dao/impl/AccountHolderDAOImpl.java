package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.AccountHolder;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Model
public class AccountHolderDAOImpl extends AbstractDAO<AccountHolder,Integer> {
    @Override
    protected Class<AccountHolder> getEntityType() {
        return AccountHolder.class;
    }
    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
    public List<AccountHolder> findAllByAccountId(Connection connection, int accountId) throws SQLException {
        String query = "select * from account_holder where account_id=?";
        return executeSelect(connection, query, accountId);
    }
}
