package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.TransactionLog;
import com.sonnguyen.individual.nhs.dao.Idao.ITransactionLogDAO;

import javax.enterprise.inject.Model;

@Model
public class TransactionLogDAO extends DAO<TransactionLog,Integer> implements ITransactionLogDAO {
    @Override
    public Class<TransactionLog> getEntityClass() {
        return TransactionLog.class;
    }
}
