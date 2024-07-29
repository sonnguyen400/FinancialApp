package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.model.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IAccountDAO extends AbstractDAO<Account,Integer> {
    BigDecimal updateBalanceByAccountId(Connection connection,Integer accountId, BigDecimal bigDecimal) throws SQLException;
    BigDecimal findBalanceByAccountId(int accountId);
    Optional<Account> findAccountByAccountNumber(String accountNumber);
    List<Account> findByCustomerIdAndType(Integer customerId, AccountType accountType);
    List<Account> findAllByCustomerId(Integer customerId);
    List<Account> findByStatusAndTypeAndCustomerId(AccountStatus status, AccountType type, Integer customerId);
    Account findDefaultAccountByCustomerId(Integer customerId);
    Integer updateAccountStatusByAccountId(Connection connection,Integer accountId, AccountStatus status) throws SQLException;

}
