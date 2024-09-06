package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.dao.idao.ITransactionLogDAO;
import com.sonnguyen.individual.nhs.model.TransactionLog;

import javax.enterprise.inject.Model;

@Model
public class TransactionLogDAOImpl extends AbstractDAO<TransactionLog,Integer> implements ITransactionLogDAO {
    @Override
    protected Class<TransactionLog> getEntityType() {
        return TransactionLog.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
}
