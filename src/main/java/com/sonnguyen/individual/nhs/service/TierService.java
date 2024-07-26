package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.model.Tier;
import com.sonnguyen.individual.nhs.service.iservice.ITierService;
import com.sonnguyen.individual.nhs.dao.idao.ITierDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Optional;

@Model
public class TierService implements ITierService {
    @Inject
    ITierDAO tierDAO;
    @Override
    public Optional<Tier> findById(int id)  {
        try {
            return tierDAO.findById(id);
        } catch (SQLException e) {
            return null;
        }
    }
}
