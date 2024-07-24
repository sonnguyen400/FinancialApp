package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Model.Tier;

import java.sql.SQLException;
import java.util.Optional;

public interface ITierService {
    Optional<Tier> findById(int id);
}
