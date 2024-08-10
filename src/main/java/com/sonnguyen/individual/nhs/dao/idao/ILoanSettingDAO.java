package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.dao.core.GeneralDAO;
import com.sonnguyen.individual.nhs.model.LoanSetting;

import java.util.Optional;

public interface ILoanSettingDAO extends GeneralDAO<LoanSetting,Integer> {
    Optional<LoanSetting> findByTerm(Integer term);
}
