package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Model.SavingsInfo;

import java.util.Optional;

public interface ISavingsInfoService {
    Optional<SavingsInfo> findByAccountId(int accountId);
}
