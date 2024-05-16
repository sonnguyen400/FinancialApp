package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Model.Transfer;
import com.sonnguyen.individual.nhs.Repository.GeneralRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.ITransferRepository;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ITransferService;
import org.jboss.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

@Model
public class TransferService implements ITransferService {
    @Inject
    ITransferRepository transferRepository;
    @Inject
    TransactionService transactionService;
    @Inject
    IAccountService accountService;
    Logger logger=Logger.getLogger(this.getClass());

    @Override
    public Transfer startTransfer(Transfer transfer) {
        return GeneralRepository.createTransactional((connection)-> startTransfer(connection,transfer));
    }

    @Override
    public Transfer startTransfer(Connection connection, Transfer transfer) throws SQLException {
        logger.debug(transfer.getTransaction());
        accountService.updateBalanceByAccountId(connection,transfer.getTransaction().getAccountId(),transfer.getTransaction().getValue().negate());
        accountService.updateBalanceByAccountId(connection,transfer.getAccountId(),transfer.getTransaction().getValue());
        Transaction transaction=transactionService.createTransaction(connection,transfer.getTransaction());
        return transferRepository.findById(transferRepository.startTransfer(connection,transfer).getId()).get();
    }

}
