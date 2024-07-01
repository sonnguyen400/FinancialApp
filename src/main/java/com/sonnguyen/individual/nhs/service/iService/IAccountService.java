package com.sonnguyen.individual.nhs.service.iService;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.SavingsInfor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface IAccountService{
    Optional<Account> findSavingsAccountsByCustomerId(Integer customerId);
    Account findPrincipalAccountByCustomerId(Integer customerId);

    Optional<Account> findAccountByAccountNumber(String username);
    BigDecimal updateBalanceByAccountId(Connection connection,Integer accountId, BigDecimal value) throws SQLException;
    Collection<Account> findAllByCustomerId(Integer customerId);
    Account createSavingsAccount(Integer customerId, SavingsInfor savingsInfor);
    Optional<Account> findOpeningSavingByCustomerId(Integer customerId);
    Account findDefaultAccountByCustomerId(Integer customerId);

}