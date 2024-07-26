package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.model.AccountHolder;
import com.sonnguyen.individual.nhs.dao.idao.IAccountHolderDAO;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Model
public class AccountHolderDAO extends DAO<AccountHolder,Integer> implements IAccountHolderDAO {
    @Override
    public Class<AccountHolder> getEntityClass() {
        return AccountHolder.class;
    }
    @Override
    public List<AccountHolder> findAllByAccountId(Connection connection,int accountId) throws SQLException {
        String query="select * from account_holder where account_id=?";
        return executeSelect(connection,query,accountId);

    }


}
