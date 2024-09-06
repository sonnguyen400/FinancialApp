package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.dao.idao.IMemberShipDao;
import com.sonnguyen.individual.nhs.model.Membership;

import javax.enterprise.inject.Model;

@Model
public class MembershipDAOImpl extends AbstractDAO<Membership,Integer> implements IMemberShipDao {
    @Override
    protected Class<Membership> getEntityType() {
        return Membership.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
}
