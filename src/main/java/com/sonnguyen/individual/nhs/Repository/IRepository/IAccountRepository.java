package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface IAccountRepository extends AbstractRepository<Account,Integer> {
    Optional<Account> findByUsername(String username);
    String findPINByAccountId(int accountId);
    BigDecimal updateBalanceByAccountId(Connection connection,Integer accountId, BigDecimal bigDecimal) throws SQLException;
    BigDecimal findBalanceByAccountId(int accountId);
    Account save(Account account);

    public Optional<Account> findAccountByAccountNumber(String accountNumber);

}
