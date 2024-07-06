package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Tier;
import com.sonnguyen.individual.nhs.Service.IService.ITierService;
import com.sonnguyen.individual.nhs.dao.GeneralDAO;
import com.sonnguyen.individual.nhs.dao.Idao.ITierDAO;

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
