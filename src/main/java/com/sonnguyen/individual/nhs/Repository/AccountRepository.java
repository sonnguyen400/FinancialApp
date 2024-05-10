package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;
import com.sonnguyen.individual.nhs.Utils.AccountType;

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

    @Override
    public List<Account> findByCustomerIdAndType(Integer customerId, AccountType accountType) {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="select * from account where id in\n" +
                "(select account_id from account_holder where account_type=? and customer_id=?)";
        if(connection!=null){
            try {
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,accountType.value);
                preparedStatement.setInt(2,customerId);
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1));
                }
                return executeSelect(query,accountType.value,customerId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


}
