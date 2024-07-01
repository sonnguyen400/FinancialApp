package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.model.Transaction;
import com.sonnguyen.individual.nhs.model.Transfer;
import com.sonnguyen.individual.nhs.dao.GeneralDAO;
import com.sonnguyen.individual.nhs.dao.Idao.ITransferDAO;
import com.sonnguyen.individual.nhs.service.iService.IAccountService;
import com.sonnguyen.individual.nhs.service.iService.ITransferService;
import org.jboss.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

@Model
public class TransferService implements ITransferService {
    @Inject
    ITransferDAO transferRepository;
    @Inject
    TransactionService transactionService;
    @Inject
    IAccountService accountService;
    Logger logger=Logger.getLogger(this.getClass());

    @Override
    public Transfer startTransfer(Transfer transfer) {
        return GeneralDAO.createTransactional((connection)-> startTransfer(connection,transfer));
    }

    @Override
    public Transfer startTransfer(Connection connection, Transfer transfer) throws SQLException {
        logger.debug(transfer.getTransaction());
        accountService.updateBalanceByAccountId(connection,transfer.getTransaction().getAccountId(),transfer.getTransaction().getValue().negate());
        accountService.updateBalanceByAccountId(connection,transfer.getAccountId(),transfer.getTransaction().getValue());
        Transaction transaction=transactionService.createTransaction(connection,transfer.getTransaction());
        transfer.setTransactionId(transaction.getId());
        transfer=transferRepository.startTransfer(connection,transfer);
        return transfer;
    }

}
