package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.AccountHolder;
import com.sonnguyen.individual.nhs.Model.SavingsInfor;
import com.sonnguyen.individual.nhs.dao.GeneralDAO;
import com.sonnguyen.individual.nhs.dao.Idao.IAccountDAO;
import com.sonnguyen.individual.nhs.dao.Idao.IAccountHolderDAO;
import com.sonnguyen.individual.nhs.dao.Idao.ISavingDAO;

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
    public SavingsInfor createSaving(Integer customer,SavingsInfor savingInfor) {
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
