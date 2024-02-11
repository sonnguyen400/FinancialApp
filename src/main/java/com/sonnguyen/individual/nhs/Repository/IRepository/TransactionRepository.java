package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Transaction;

public class TransactionRepository extends Repository<Transaction,Integer> {
    private static final TransactionRepository transactionRepository = new TransactionRepository();
    public static TransactionRepository getInstance() {
        return transactionRepository;
    }
}
