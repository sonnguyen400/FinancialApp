package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.TransactionLog;

import javax.enterprise.inject.Model;

@Model
public class TransactionLogDAOImpl extends AbstractDAO<TransactionLog,Integer> {
    @Override
    protected Class<TransactionLog> getEntityType() {
        return TransactionLog.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
}
