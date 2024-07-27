package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.impl.MembershipDAOImpl;
import com.sonnguyen.individual.nhs.model.Membership;
import com.sonnguyen.individual.nhs.service.iservice.IMembershipService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Optional;

@Model
public class MembershipService implements IMembershipService {
    @Inject
    MembershipDAOImpl memberShipDao;
    @Override
    public Optional<Membership> findById(int id) {
        return memberShipDao.findById(id);
    }
}
