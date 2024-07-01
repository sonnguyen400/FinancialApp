package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.AccountHolder;
import com.sonnguyen.individual.nhs.dao.Idao.IAccountHolderDAO;

import javax.enterprise.inject.Model;
@Model
public class AccountHolderDAO extends DAO<AccountHolder,Integer> implements IAccountHolderDAO {
    @Override
    public Class<AccountHolder> getEntityClass() {
        return AccountHolder.class;
    }


}
