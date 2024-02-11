package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.TransactionLog;

public class TransactionLogRepository extends Repository<TransactionLog,Integer> {
    private static final TransactionLogRepository transactionLogRepository = new TransactionLogRepository();
    public static TransactionLogRepository getInstance() {
        return transactionLogRepository;
    }
}
