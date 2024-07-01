package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.Idao.ITransactionDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class TransactionLogService {
    @Inject
    private ITransactionDAO transactionRepository;


}
