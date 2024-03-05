package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Repository.IRepository.ITransactionRepository;

import javax.enterprise.inject.Model;

@Model
public class TransactionRepository extends Repository<Transaction,Integer> implements ITransactionRepository {
    @Override
    public Class<Transaction> getEntityClass() {
        return Transaction.class;
    }
}
