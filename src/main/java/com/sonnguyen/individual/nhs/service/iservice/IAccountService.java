package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.Constant.AccountStatus;
import com.sonnguyen.individual.nhs.Constant.AccountType;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.SavingsInfo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IAccountService{
    Optional<Account> findById(int id);
    Optional<Account> findByAccountNumber(String username);
    Collection<Account> findAllByCustomerId(Integer customerId);
    void createSavingsAccount(Integer customerId, SavingsInfo savingsInfor);
    List<Account> findByStatusAndTypeAndCustomerId(AccountStatus status,AccountType accountType, Integer customerId);
    Account findDefaultAccountByCustomerId(Integer customerId);
    List<Account> findPrincipleByCustomerId(Integer customerId);
}
