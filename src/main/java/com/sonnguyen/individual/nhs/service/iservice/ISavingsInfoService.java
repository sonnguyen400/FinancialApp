package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.SavingsInfo;

import java.util.Optional;

public interface ISavingsInfoService {
    Optional<SavingsInfo> findByAccountId(int accountId);
}
