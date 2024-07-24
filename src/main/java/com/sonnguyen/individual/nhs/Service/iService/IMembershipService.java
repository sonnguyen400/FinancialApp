package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Model.Membership;

import java.sql.SQLException;
import java.util.Optional;

public interface IMembershipService {
    Optional<Membership> findById(int id);
}
