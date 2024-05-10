package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Exception.FailureTransaction;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Repository.AccountHolderRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;
import com.sonnguyen.individual.nhs.Utils.AccountType;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Model
public class AccountService implements IAccountService {
    @Inject
    private IAccountRepository accountRepository;
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }


    @Override
    public Account findPrincipalAccountByCustomerId(Integer customerId) {
        return accountRepository.findByCustomerIdAndType(customerId, AccountType.PRINCIPAL).get(0);
    }
    @Override
    public List<Account> findSavingsAccountsByCustomerId(Integer customerId) {
        return accountRepository.findByCustomerIdAndType(customerId, AccountType.SAVINGS);
    }

    @Override
    public Optional<Account> findAccountByAccountNumber(String username) {
        return accountRepository.findAccountByAccountNumber(username);
    }

    @Override
    public BigDecimal updateBalanceByAccountId(Connection connection,Integer accountId, BigDecimal value) throws SQLException {
        return accountRepository.updateBalanceByAccountId(connection,accountId, value);
    }

}
