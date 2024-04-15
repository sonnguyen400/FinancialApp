package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Model.Transfer;
import com.sonnguyen.individual.nhs.Repository.GeneralRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.ITransferRepository;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ITransferService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class TransferService implements ITransferService {
    @Inject
    ITransferRepository transferRepository;
    @Inject
    TransactionService transactionService;
    @Inject
    IAccountService accountService;

    @Override
    public Transfer startTransfer(Transfer transfer) {
        return GeneralRepository.createTransactional((connection)->{
            accountService.updateBalanceByAccountId(connection,transfer.getTransaction().getAccountId(),transfer.getTransaction().getValue().negate());
            accountService.updateBalanceByAccountId(connection,transfer.getAccountId(),transfer.getTransaction().getValue());
            Transaction transaction=transactionService.createTransaction(connection,transfer.getTransaction());
            transfer.setTransactionId(transaction.getId());
            transferRepository.startTransfer(connection,transfer);
            return transfer;
        });
    }
}
