package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.dao.Idao.ITransactionDAO;
import com.sonnguyen.individual.nhs.Service.IService.ITransactionService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

@Model
public class TransactionLogService {
    @Inject
    private ITransactionDAO transactionRepository;


}
