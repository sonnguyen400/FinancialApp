package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.dao.core.GeneralDAO;
import com.sonnguyen.individual.nhs.model.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IAccountDAO extends GeneralDAO<Account, Integer> {
    int updateBalanceByAccountId(Connection connection, Integer accountId, BigDecimal bigDecimal) throws SQLException;
    Optional<Account> findBranchPrincipalAccount(int branchId);
    BigDecimal findBalanceByAccountId(int accountId);
    Optional<Account> findAccountByAccountNumber(String accountNumber);
    List<Account> findByCustomerIdAndType(Integer customerId, AccountType accountType);
    List<Account> findAllByCustomerId(Integer customerId);
    List<Account> findByStatusAndTypeAndCustomerId(AccountStatus status, AccountType type, Integer customerId);
    Optional<Account> findDefaultAccountByCustomerId(Integer customerId);
    Integer updateAccountStatusByAccountId(Connection connection,Integer accountId, AccountStatus status) throws SQLException;
    Optional<Account> findById(Integer accountId);
    void updateAccountStatus(Connection connection, Integer accountId, int status) throws SQLException;
}
