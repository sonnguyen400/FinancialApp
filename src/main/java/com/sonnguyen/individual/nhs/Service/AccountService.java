package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;

import javax.inject.Inject;

public class AccountService implements IAccountService {
    @Inject
    private IAccountRepository accountRepository;


}
