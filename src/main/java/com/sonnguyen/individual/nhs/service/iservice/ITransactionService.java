package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Transaction;

import java.util.List;

public interface ITransactionService {
    List<Transaction> findHistoryByAccountId(Integer accountId);
}
