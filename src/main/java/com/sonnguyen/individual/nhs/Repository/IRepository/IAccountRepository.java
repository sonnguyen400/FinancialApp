package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Exception.FailureTransaction;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Utils.Transactional;

import java.util.Optional;

public interface IAccountRepository extends AbstractRepository<Account,Integer> {
    public Optional<Account> findByUsername(String username);
    public String findPINByAccountId(int accountId);
    public void transactionStart(Transactional transactional) throws FailureTransaction;
    public Optional<Account> findAccountByAccountNumber(String accountNumber);
}
