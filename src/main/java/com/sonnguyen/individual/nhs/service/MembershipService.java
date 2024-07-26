package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.model.Membership;
import com.sonnguyen.individual.nhs.service.iservice.IMembershipService;
import com.sonnguyen.individual.nhs.dao.idao.IMemberShipDao;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Optional;

@Model
public class MembershipService implements IMembershipService {
    @Inject
    IMemberShipDao memberShipDao;
    @Override
    public Optional<Membership> findById(int id) {
        try {
            return memberShipDao.findById(id);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
