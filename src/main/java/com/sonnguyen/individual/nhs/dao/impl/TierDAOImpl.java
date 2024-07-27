package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Tier;

import javax.enterprise.inject.Model;

@Model
public class TierDAOImpl extends AbstractDAO<Tier,Integer> {
    @Override
    protected Class<Tier> getEntityType() {
        return Tier.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
}
