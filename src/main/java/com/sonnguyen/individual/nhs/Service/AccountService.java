package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Exception.FailureTransaction;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Repository.AccountHolderRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;

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
    @Inject
    private AccountHolderRepository accountHolder;
    @Inject
    private ICustomerService customerService;
    @Override
    public Account createNewAccount(Account account,Customer customer) throws FailureTransaction {
        account.setCustomers(List.of(customer));
        return  accountRepository.save(account);
    }
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public String findPINByAccountId(Integer id) {
        return accountRepository.findPINByAccountId(id);
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
