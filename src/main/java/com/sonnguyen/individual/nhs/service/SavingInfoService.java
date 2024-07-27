package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.impl.SavingDAOImp;
import com.sonnguyen.individual.nhs.model.SavingsInfo;
import com.sonnguyen.individual.nhs.service.iservice.ISavingsInfoService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Optional;

@Model
public class SavingInfoService implements ISavingsInfoService {
    @Inject
    SavingDAOImp savingDAO;

    @Override
    public Optional<SavingsInfo> findByAccountId(int accountId) {
        try {
            return savingDAO.findByAccountId(accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
