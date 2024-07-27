package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.impl.TransactionDAOImpl;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class TransactionLogService {
    @Inject
    private TransactionDAOImpl transactionDAO;


}
