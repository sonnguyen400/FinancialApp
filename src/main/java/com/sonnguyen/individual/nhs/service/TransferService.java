package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.Constant.AccountStatus;
import com.sonnguyen.individual.nhs.Constant.TransactionStatus;
import com.sonnguyen.individual.nhs.Constant.TransactionType;
import com.sonnguyen.individual.nhs.dao.impl.AccountDAOImp;
import com.sonnguyen.individual.nhs.dao.impl.TierDAOImpl;
import com.sonnguyen.individual.nhs.dao.impl.TransactionDAOImpl;
import com.sonnguyen.individual.nhs.dao.impl.TransferDAOImpl;
import com.sonnguyen.individual.nhs.dao_v2.DBTransaction;
import com.sonnguyen.individual.nhs.exception.CommitTransactionException;
import com.sonnguyen.individual.nhs.exception.FailureTransaction;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Tier;
import com.sonnguyen.individual.nhs.model.Transaction;
import com.sonnguyen.individual.nhs.model.Transfer;
import com.sonnguyen.individual.nhs.service.iservice.ITransferService;
import org.jboss.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Model
public class TransferService implements ITransferService {
    @Inject
    TransferDAOImpl transferDAO;
    @Inject
    TransactionDAOImpl transactionDAO;
    @Inject
    AccountDAOImp accountDAO;
    @Inject
    DBTransaction dbTransaction;
    @Inject
    TierDAOImpl tierDAO;

    Logger logger=Logger.getLogger(this.getClass());


    @Override
    public Transfer startTransfer(Connection connection, Transfer transfer) throws SQLException {
        accountDAO.updateBalanceByAccountId(connection,transfer.getTransaction().getAccountId(),transfer.getTransaction().getAmount().negate());
        accountDAO.updateBalanceByAccountId(connection,transfer.getAccountId(),transfer.getTransaction().getAmount());
        Integer transaction=transactionDAO.executeInsert(connection, transfer.getTransaction());
        transfer.setTransactionId(transaction);
        transfer.setId(transferDAO.executeInsert(connection,transfer));
        return transfer;
    }


    @Override
    public Transfer transferCommit(String transactionRefNumber) throws FailureTransaction {
        return dbTransaction.startTransaction(Transfer.class,connection -> transferCommit(connection,transactionRefNumber),connection -> {
            transactionDAO.updateStatusByRefNumber(connection,TransactionStatus.FAILURE,transactionRefNumber);
            return new Transfer();
        });
    }

    @Override
    public Transfer transferCommit(Connection connection, String transactionRefNumber) throws SQLException,CommitTransactionException {
        //Get equivalent transaction
        List<Transaction> transactions=transactionDAO.findAllByRefNumber(connection,transactionRefNumber);
        Transaction transferTransaction=null;
        for(Transaction transaction:transactions){
            if(transaction.getTransactionType()==TransactionType.TRANSFER.value){
                transferTransaction=transaction;
                break;
            }
        }
        if(transferTransaction==null) throw new CommitTransactionException("Unable to commit transaction! Transference transaction could not be found");
        Account transferAccount=accountDAO.findById(connection,transferTransaction.getAccountId()).orElseThrow(()->new CommitTransactionException("Account not found"));
        if(transferAccount.getStatus()!=AccountStatus.OPEN.value) throw new CommitTransactionException("Account isn't opening");
        Tier tier=tierDAO.findById(connection,transferAccount.getTierID()).orElseThrow(()->new CommitTransactionException("Tier not found"));
        double amountAfterTransfer=transferAccount.getBalance().subtract(transferTransaction.getAmount()).doubleValue();
        if(amountAfterTransfer<=0 && amountAfterTransfer*(-1)>tier.getOverdraftLimit().doubleValue()){
            throw new CommitTransactionException("Over balance");
        }

        //Update balance
        Transfer transfer=transferDAO.findByTransactionId(connection,transferTransaction.getId()).orElseThrow(()->new CommitTransactionException("Transaction is iterrupted due to internal error"));
        Account receiveAccount=accountDAO.findById(connection,transfer.getAccountId()).orElseThrow(()->new CommitTransactionException("Account not found"));
        if(receiveAccount.getStatus()!=AccountStatus.OPEN.value) throw new CommitTransactionException("Receive Account isn't opening");
        transfer.setTransaction(transferTransaction);
        accountDAO.updateBalanceByAccountId(connection,transfer.getTransaction().getAccountId(),transfer.getTransaction().getAmount().negate());
        accountDAO.updateBalanceByAccountId(connection,transfer.getAccountId(),transfer.getTransaction().getAmount());
        transactionDAO.updateStatus(connection,transactions.get(0).getId(),TransactionStatus.SUCCESS.value);
        transactionDAO.updateStatus(connection,transactions.get(1).getId(),TransactionStatus.SUCCESS.value);
        return transfer;
    }


    @Override
    public String init(Transfer send) {
        return dbTransaction.startTransaction(String.class,connection -> init(connection,send));
    }
    @Override
    public String init(Connection connection, Transfer send) throws SQLException {
        //ReferenceNumber
        String referenceNumber= send.getTransaction().getAccountId()+""+send.getAccountId()+Instant.now();
        //ReferenceTransfer
        Transfer receive=new Transfer();
        receive.setAccountId(send.getTransaction().getAccountId());
        receive.setMessage(send.getMessage());
        //ReferenceTransaction
        Transaction receiveTransaction=new Transaction();
        receiveTransaction.setAmount(send.getTransaction().getAmount());
        receiveTransaction.setAccountId(send.getAccountId());
        receiveTransaction.setStatus(TransactionStatus.PENDING.value);
        receiveTransaction.setTransactionType(TransactionType.RECEIVE.value);
        receiveTransaction.setDescription(send.getTransaction().getDescription());
        receiveTransaction.setReferenceNumber(referenceNumber);
        //Transaction
        send.getTransaction().setTransactionType(TransactionType.TRANSFER.value);
        send.getTransaction().setStatus(TransactionStatus.PENDING.value);
        send.getTransaction().setReferenceNumber(referenceNumber);

        //Start transfer
        send.setTransactionId(transactionDAO.executeInsert(connection,send.getTransaction()));
        transferDAO.executeInsert(connection,send);

        receive.setTransactionId(transactionDAO.executeInsert(connection,receiveTransaction));
        transferDAO.executeInsert(connection,receive);
        return referenceNumber;
    }

}
