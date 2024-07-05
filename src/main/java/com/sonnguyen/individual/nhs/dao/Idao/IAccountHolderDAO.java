package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.AccountHolder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IAccountHolderDAO extends AbstractDAO<AccountHolder,Integer> {
    List<AccountHolder> findAllByAccountId(Connection connection,int accountId) throws SQLException;
}
