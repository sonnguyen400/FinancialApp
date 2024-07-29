package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.constant.Rollover;
import com.sonnguyen.individual.nhs.model.SavingsInfo;

import java.util.List;
import java.util.Optional;

public interface ISavingsInfoService {
    Optional<SavingsInfo> findByAccountId(int accountId);
    Optional<SavingsInfo> findById(int accountId);
    Optional<SavingsInfo> completeSavings(SavingsInfo savingsInfo, int beneficiaryAccountId);
    List<SavingsInfo> findAllMaturity();
}
