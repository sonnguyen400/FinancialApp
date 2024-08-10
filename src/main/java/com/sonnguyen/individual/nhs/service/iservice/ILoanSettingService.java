package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.LoanSetting;

import java.util.List;
import java.util.Optional;

public interface ILoanSettingService {
    List<LoanSetting> findAll();
    Optional<LoanSetting> findByTerm(Integer term);

    Optional<LoanSetting> findById(Integer id);
}
