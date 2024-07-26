package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.model.TransactionLog;
import com.sonnguyen.individual.nhs.dao.idao.ITransactionLogDAO;

import javax.enterprise.inject.Model;

@Model
public class TransactionLogDAO extends DAO<TransactionLog,Integer> implements ITransactionLogDAO {
    @Override
    public Class<TransactionLog> getEntityClass() {
        return TransactionLog.class;
    }
}
