package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.model.AccountHolder;
import com.sonnguyen.individual.nhs.model.SavingsInfo;
import com.sonnguyen.individual.nhs.dao.GeneralDAO;
import com.sonnguyen.individual.nhs.dao.idao.IAccountDAO;
import com.sonnguyen.individual.nhs.dao.idao.IAccountHolderDAO;
import com.sonnguyen.individual.nhs.dao.idao.ISavingDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class SavingService {
    @Inject
    ISavingDAO savingDao;
    @Inject
    IAccountDAO accountDAO;
    @Inject
    IAccountHolderDAO accountHolderRepository;
    public SavingsInfo createSaving(Integer customer, SavingsInfo savingInfor) {
        return GeneralDAO.createTransactional((connection -> {
            Integer accountId =accountDAO.executeInsert(connection, savingInfor.getAccount());
            AccountHolder accountHolder=new AccountHolder(accountId,customer);
            accountHolderRepository.executeInsert(connection,accountHolder);
            Integer savingId=savingDao.executeInsert(connection,savingInfor);
            savingInfor.setId(savingId);
            return savingInfor;
        }));
    }
}
