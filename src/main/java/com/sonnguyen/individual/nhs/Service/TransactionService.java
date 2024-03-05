package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Repository.IRepository.ITransactionRepository;
import com.sonnguyen.individual.nhs.Service.IService.ITransactionService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class TransactionService implements ITransactionService {
    @Inject
    private ITransactionRepository transactionRepository;
}
