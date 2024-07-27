package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Account;

import javax.enterprise.inject.Model;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Model
public class AccountDAOImp extends AbstractDAO<Account, Integer> {

    @Override
    protected Class<Account> getEntityType() {
        return Account.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }

    public BigDecimal findBalanceByAccountId(int accountId) {
        Connection connection = getConnection();
        String query = "Select balance from account where id=?";
        List<BigDecimal> decimals = executeSelect(connection,query, BigDecimal.class, accountId);
        if (decimals.size() == 1) return decimals.get(0);
        return null;
    }

    public void updateBalanceByAccountId(Connection connection, Integer accountId, BigDecimal disparity) throws SQLException, NullPointerException {
        String query = "update account set balance=balance+? where id=?";
        executeUpdate(connection, query, disparity, accountId);
    }

    public Optional<Account> findAccountByAccountNumber(String accountNumber) {
        Connection connection = getConnection();
        String query = "Select * from account where account_number=?";
        List<Account> accounts = executeSelect(connection, query, Account.class, accountNumber);
        try {
            connection.close();
        } catch (SQLException | NullPointerException e) {
            throw new RuntimeException(e);
        }
        if (!accounts.isEmpty()) return Optional.of(accounts.get(0));
        return Optional.empty();
    }

    public List<Account> findByCustomerIdAndType(Integer customerId, com.sonnguyen.individual.nhs.Constant.AccountType accountType) {
        String query = "select * from account where id in (select account_id from account_holder where account_type=? and customer_id=?)";
        return executeSelect(query,accountType.value, customerId);
    }
    public List<Account> findAllByCustomerId(Integer customerId) {
        String query="Select * from account where id in (select account_id from account_holder where customer_id=?)";
        return executeSelect(query,customerId);
    }
    public List<Account> findByStatusAndTypeAndCustomerId(com.sonnguyen.individual.nhs.Constant.AccountStatus status, com.sonnguyen.individual.nhs.Constant.AccountType type, Integer customerId) {
        String query = "select * from account where status=? and account_type=? and id in (Select account_id from account_holder where customer_id=?)";
        return executeSelect(query,status.value,type.value,customerId);
    }

    public Optional<Account> findDefaultAccountByCustomerId(Integer customerId) {
        String query = "Select * from account where id in (select account_id from account_holder where customer_id=? && is_default=true)";
        List<Account> accounts=executeSelect(query,customerId);
        if (accounts.isEmpty()) return Optional.empty();
        return Optional.of(accounts.get(0));
    }
    public Integer updateAccountStatusByAccountId(Connection connection,Integer accountId, com.sonnguyen.individual.nhs.Constant.AccountStatus status) throws SQLException {
        String query = "update account set status=? where id=?";
        return executeUpdate(connection, query, status.value, accountId);
    }
}
