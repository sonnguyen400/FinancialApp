package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;
import com.sonnguyen.individual.nhs.Constant.AccountType;

import javax.enterprise.inject.Model;
import java.math.BigDecimal;
import java.sql.Connection;
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
    public BigDecimal updateBalanceByAccountId(Connection connection,Integer accountId,BigDecimal disparity) throws SQLException,NullPointerException {
        String query="update account set balance=balance+? where id=?";
        executeUpdate(connection,query,disparity,accountId);
        return findBalanceByAccountId(accountId);
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
        String query="select * from account where id in\n" +
                "(select account_id from account_holder where account_type=? and customer_id=?)";
        try {
            return executeSelect(query,accountType.value,customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Account> findAllByCustomerId(Integer customerId) {
        String query="Select * from account where id in (select account_id from account_holder where customer_id=?)";
        try {
            return executeSelect(query,customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
    public Optional<Account> findSavingAccountByCustomerId(Integer customerId){
        String query="Select * from account where id in (select account_id from account_holder where customer_id=? and account_type=?)";
        List<Account> accounts=List.of();
        try {
            accounts=executeSelect(query,customerId,AccountType.SAVINGS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(accounts.size()>0){
            return Optional.of(accounts.get(0));
        }else{
            return Optional.empty();
        }

    }

    @Override
    public List<Account> findByStatusAndTypeAndCustomerId(String status, String type, Integer customerId) {
        String query="select * from account where status=? and account_type=? and id in (Select account_id from account_holder where customer_id=?)";
        try {
            return executeSelect(query,status,type,customerId);
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Account findDefaultAccountByCustomerId(Integer customerId) {
        String query="Select * from account where id in (select account_id from account_holder where customer_id=? && is_default=true)";
        List<Account> accounts=List.of();
        try {
            accounts=executeSelect(query,customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(accounts==null||accounts.size()==0) return null;
        return accounts.get(0);
    }


}
