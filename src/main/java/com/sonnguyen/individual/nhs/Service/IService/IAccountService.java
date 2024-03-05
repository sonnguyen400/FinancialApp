package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Exception.FailureTransaction;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;

import java.util.Optional;

public interface IAccountService{
    Account createNewAccount(Account account, Customer customer) throws FailureTransaction;
    Optional<Account> findByUsername(String username) ;
}
