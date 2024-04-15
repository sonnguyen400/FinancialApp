package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;

import javax.enterprise.inject.Model;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            List<Account> accountList=find("Select * from Account where username=?",username);
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
    public BigDecimal findBalanceByAccountId(int accountId) {
        String query="Select balance from account where id=?";
        return executeSelect(query, BigDecimal.class,accountId);
    }

    @Override
    public BigDecimal updateBalanceByAccountId(Connection connection,Integer accountId,BigDecimal disparity) throws SQLException {
        BigDecimal bigDecimal=findBalanceByAccountId(accountId);
        bigDecimal=bigDecimal.add(disparity);
        String query="update account set balance=? where id=?";
        executeUpdate(connection,query,bigDecimal,accountId);
        return bigDecimal;
    }


    @Override
    public Optional<Account> findAccountByAccountNumber(String accountNumber) {
        String query="Select * from account where account_number=?";
        List<Account> accounts;
        try {
             accounts= find(query,accountNumber);
        }catch (SQLException e){
            return Optional.empty();
        }
        if(accounts.size()==0){
            return Optional.empty();
        }
        return Optional.of(accounts.get(0));

    }
    public Account save(Account account) {
        try {
            account=insert(account);
            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
