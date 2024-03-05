package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;

import javax.enterprise.inject.Model;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Model
public class AccountRepository extends Repository<Account,Integer> implements IAccountRepository {
    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    public Optional<Account> findByUsername(String username)  {
        try{
            List<Account> accountList=executeSelect("Select * from Account where username=?",Account.class,username);
            if(accountList.size()!=1) return Optional.empty();
            return Optional.of(accountList.get(0));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
