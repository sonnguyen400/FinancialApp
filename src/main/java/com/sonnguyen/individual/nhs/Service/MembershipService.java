package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Membership;
import com.sonnguyen.individual.nhs.Service.IService.IMembershipService;
import com.sonnguyen.individual.nhs.dao.Idao.IMemberShipDao;

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
