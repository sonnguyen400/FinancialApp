package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Constant.AccountType;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IAccountDAO extends AbstractDAO<Account,Integer> {
    Optional<Account> findByUsername(String username);
    BigDecimal updateBalanceByAccountId(Connection connection,Integer accountId, BigDecimal bigDecimal) throws SQLException;
    BigDecimal findBalanceByAccountId(int accountId);

    Optional<Account> findAccountByAccountNumber(String accountNumber);
    List<Account> findByCustomerIdAndType(Integer customerId, AccountType accountType);
    List<Account> findAllByCustomerId(Integer customerId);

}
