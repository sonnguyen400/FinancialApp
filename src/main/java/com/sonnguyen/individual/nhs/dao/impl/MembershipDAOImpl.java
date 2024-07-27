package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Membership;

import javax.enterprise.inject.Model;

@Model
public class MembershipDAOImpl extends AbstractDAO<Membership,Integer> {
    @Override
    protected Class<Membership> getEntityType() {
        return Membership.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
}
