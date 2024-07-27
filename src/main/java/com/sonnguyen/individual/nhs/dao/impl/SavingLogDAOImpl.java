package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Savinglog;

import javax.enterprise.inject.Model;

@Model
public class SavingLogDAOImpl extends AbstractDAO<Savinglog,Integer> {
    @Override
    protected Class<Savinglog> getEntityType() {
        return Savinglog.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
}
