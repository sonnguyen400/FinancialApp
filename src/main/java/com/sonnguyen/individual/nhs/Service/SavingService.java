package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Constant.AccountType;
import com.sonnguyen.individual.nhs.Model.AccountHolder;
import com.sonnguyen.individual.nhs.Model.SavingsInfor;
import com.sonnguyen.individual.nhs.Repository.GeneralRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountHolderRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.ISavingRepository;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class SavingService {
    @Inject
    ISavingRepository savingRepository;
    @Inject
    IAccountRepository accountRepository;
    @Inject
    IAccountHolderRepository accountHolderRepository;
    public SavingsInfor createSaving(Integer customer,SavingsInfor savingInfor) {
        return GeneralRepository.createTransactional((connection -> {
            Integer accountId =accountRepository.executeInsert(connection, savingInfor.getAccount());
            AccountHolder accountHolder=new AccountHolder(accountId,customer, AccountType.SAVINGS.value);
            accountHolderRepository.executeInsert(connection,accountHolder);
            Integer savingId=savingRepository.executeInsert(connection,savingInfor);
            savingInfor.setId(savingId);
            return savingInfor;
        }));
    }
}
