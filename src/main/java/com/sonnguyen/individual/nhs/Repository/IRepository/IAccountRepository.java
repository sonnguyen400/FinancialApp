package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Utils.AccountType;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IAccountRepository extends AbstractRepository<Account,Integer> {
    Optional<Account> findByUsername(String username);
    BigDecimal updateBalanceByAccountId(Connection connection,Integer accountId, BigDecimal bigDecimal) throws SQLException;
    BigDecimal findBalanceByAccountId(int accountId);

    Optional<Account> findAccountByAccountNumber(String accountNumber);
    List<Account> findByCustomerIdAndType(Integer customerId, AccountType accountType);

}
