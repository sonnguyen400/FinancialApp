package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.impl.AccountDAOImp;
import com.sonnguyen.individual.nhs.dao.impl.AccountHolderDAOImpl;
import com.sonnguyen.individual.nhs.dao.impl.SavingDAOImp;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class SavingService {
    @Inject
    SavingDAOImp savingDao;
    @Inject
    AccountDAOImp accountDAO;
    @Inject
    AccountHolderDAOImpl accountHolderRepository;
}
