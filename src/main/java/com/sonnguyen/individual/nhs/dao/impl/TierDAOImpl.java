package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.dao.idao.ITierDAO;
import com.sonnguyen.individual.nhs.model.Tier;

import javax.enterprise.inject.Model;

@Model
public class TierDAOImpl extends AbstractDAO<Tier,Integer> implements ITierDAO {
    @Override
    protected Class<Tier> getEntityType() {
        return Tier.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
}
