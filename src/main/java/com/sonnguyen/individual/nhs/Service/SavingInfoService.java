package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.SavingsInfo;
import com.sonnguyen.individual.nhs.Service.IService.ISavingsInfoService;
import com.sonnguyen.individual.nhs.dao.Idao.ISavingDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Optional;

@Model
public class SavingInfoService implements ISavingsInfoService {
    @Inject
    ISavingDAO savingDAO;

    @Override
    public Optional<SavingsInfo> findByAccountId(int accountId) {
        try {
            return savingDAO.findByAccountId(accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
