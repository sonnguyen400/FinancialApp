package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Account;

import java.util.Optional;

public interface IAccountRepository extends AbstractRepository<Account,Integer> {
    public Optional<Account> findByUsername(String username);
}
