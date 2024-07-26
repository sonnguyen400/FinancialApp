package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Membership;

import java.util.Optional;

public interface IMembershipService {
    Optional<Membership> findById(int id);
}
