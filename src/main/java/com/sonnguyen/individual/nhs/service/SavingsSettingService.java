package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.idao.ISavingSettingDAO;
import com.sonnguyen.individual.nhs.model.SavingsSetting;
import com.sonnguyen.individual.nhs.service.iservice.ISavingsSettingService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
@Model
public class SavingsSettingService implements ISavingsSettingService {
    @Inject
    private ISavingSettingDAO savingSettingDAO;
    @Override
    public List<SavingsSetting> findAll() {
        return savingSettingDAO.findAll();
    }

    @Override
    public Optional<SavingsSetting> findByTerm(Integer term) {
        return savingSettingDAO.findByTerm(term);
    }
}
