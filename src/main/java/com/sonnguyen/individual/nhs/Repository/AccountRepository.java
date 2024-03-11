package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Exception.FailureTransaction;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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

    @Override
    public String findPINByAccountId(int accountId) {
        String query="Select PIN from account where id=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Connection connection=getConnection();
        if(connection!=null){
            try {
                statement= connection.prepareStatement(query);
                statement.setInt(1,accountId);
               resultSet= statement.executeQuery();
               if(resultSet.next()){
                   return resultSet.getString("PIN");
               }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    connection.close();
                    statement.close();
                    resultSet.close();
                } catch (SQLException|NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    @Override
    public Optional<Account> findAccountByAccountNumber(String accountNumber) {
        String query="Select * from account where account_number=?";
        List<Account> accounts;
        try {
             accounts= executeSelect(query,accountNumber);
        }catch (SQLException e){
            return Optional.empty();
        }
        if(accounts.size()==0){
            return Optional.empty();
        }
        return Optional.of(accounts.get(0));

    }

}
