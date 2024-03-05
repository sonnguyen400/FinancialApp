package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.TransactionLog;
import com.sonnguyen.individual.nhs.Repository.IRepository.ITransactionLogRepository;

import javax.enterprise.inject.Model;

@Model
public class TransactionLogRepository extends Repository<TransactionLog,Integer> implements ITransactionLogRepository {
    @Override
    public Class<TransactionLog> getEntityClass() {
        return TransactionLog.class;
    }
}
