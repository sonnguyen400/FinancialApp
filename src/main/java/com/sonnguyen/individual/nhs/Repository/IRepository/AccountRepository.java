package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Account;

public class AccountRepository extends Repository<Account,Integer>{
    public static final AccountRepository accountRepository=new AccountRepository();
    public static AccountRepository getInstance(){
        return accountRepository;
    }
}
