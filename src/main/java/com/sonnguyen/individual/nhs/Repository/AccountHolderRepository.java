package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.AccountHolder;

import javax.enterprise.inject.Model;
import java.sql.SQLException;
@Model
public class AccountHolderRepository extends Repository<AccountHolder,AccountHolder> {
    @Override
    public Class<AccountHolder> getEntityClass() {
        return AccountHolder.class;
    }

    public void insert(Integer customerId, Integer accountId) throws SQLException {
        AccountHolder accountHolder=new AccountHolder();
        accountHolder.setAccountID(accountId);
        accountHolder.setCustomerID(customerId);
        insert(accountHolder);
    }
}
