package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.model.AccountHolder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IAccountHolderDAO extends AbstractDAO<AccountHolder,Integer> {
    List<AccountHolder> findAllByAccountId(Connection connection,int accountId) throws SQLException;
}
