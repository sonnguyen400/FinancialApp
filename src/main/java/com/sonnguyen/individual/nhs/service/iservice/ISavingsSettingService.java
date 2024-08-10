package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.SavingsSetting;

import java.util.List;
import java.util.Optional;

public interface ISavingsSettingService {
    List<SavingsSetting> findAll();
    Optional<SavingsSetting> findByTerm(Integer term);
}
