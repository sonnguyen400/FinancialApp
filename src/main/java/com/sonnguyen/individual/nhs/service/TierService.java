package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.impl.TierDAOImpl;
import com.sonnguyen.individual.nhs.model.Tier;
import com.sonnguyen.individual.nhs.service.iservice.ITierService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Optional;

@Model
public class TierService implements ITierService {
    @Inject
    TierDAOImpl tierDAO;
    @Override
    public Optional<Tier> findById(int id)  {
        return tierDAO.findById(id);
    }
}
