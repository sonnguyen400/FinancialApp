package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.dao.idao.ISavingSettingDAO;
import com.sonnguyen.individual.nhs.model.SavingsSetting;

import javax.enterprise.inject.Model;
import java.util.List;
import java.util.Optional;
@Model
public class SavingsSettingDAOImpl extends AbstractDAO<SavingsSetting,Integer> implements ISavingSettingDAO {
    @Override
    protected Class<SavingsSetting> getEntityType() {
        return SavingsSetting.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
    @Override
    public Optional<SavingsSetting> findByTerm(Integer term) {
        String sql = "SELECT * FROM savings_setting WHERE term=?";
        List<SavingsSetting> savingsSettings=executeSelect(sql,term);
        if(savingsSettings.isEmpty()) return Optional.empty();
        return Optional.of(savingsSettings.get(0));
    }
}
