package com.sonnguyen.individual.nhs.Service;
import com.sonnguyen.individual.nhs.Exception.FailureTransaction;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Repository.AccountHolderRepository;
import com.sonnguyen.individual.nhs.Repository.GeneralRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
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
        account.setBranchID(1);
        accountRepository.transactionStart(()->{
            Customer customer1=customerService.insert(customer);
            Account account1=accountRepository.insert(account);
            account.setId(account1.getId());
            customer.setId(customer1.getId());
            account.setCustomers(List.of(customer1));
            if(customer1.getId()!=0){
                accountHolder.insert(customer1.getId(),account1.getId());
            }
        });
        return account;
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

}
