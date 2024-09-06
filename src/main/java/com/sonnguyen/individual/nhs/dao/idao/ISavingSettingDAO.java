package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.dao.core.GeneralDAO;
import com.sonnguyen.individual.nhs.model.SavingsSetting;

import java.util.Optional;

public interface ISavingSettingDAO extends GeneralDAO<SavingsSetting,Integer> {
    Optional<SavingsSetting> findByTerm(Integer term);
}
