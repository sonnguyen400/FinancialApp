package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.AccountHolder;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountHolderRepository;

import javax.enterprise.inject.Model;
@Model
public class AccountHolderRepository extends Repository<AccountHolder,Integer> implements IAccountHolderRepository {
    @Override
    public Class<AccountHolder> getEntityClass() {
        return AccountHolder.class;
    }


}
