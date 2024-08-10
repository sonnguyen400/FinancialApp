package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.dao.idao.ILoanSettingDAO;
import com.sonnguyen.individual.nhs.model.LoanSetting;

import javax.enterprise.inject.Model;
import java.util.List;
import java.util.Optional;

@Model
public class LoanSettingDAOImpl extends AbstractDAO<LoanSetting,Integer> implements ILoanSettingDAO {
    @Override
    protected Class<LoanSetting> getEntityType() {
        return LoanSetting.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Optional<LoanSetting> findByTerm(Integer term) {
        String query = "SELECT * FROM loan_setting  WHERE term=?";
        List<LoanSetting> loanSettings=executeSelect(query,term);
        if(loanSettings.isEmpty()) return Optional.empty();
        return Optional.of(loanSettings.get(0));
    }
}
