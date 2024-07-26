package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Tier;

import java.util.Optional;

public interface ITierService {
    Optional<Tier> findById(int id);
}
