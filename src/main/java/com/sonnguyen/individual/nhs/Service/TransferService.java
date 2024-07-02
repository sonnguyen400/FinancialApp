package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Constant.TransactionStatus;
import com.sonnguyen.individual.nhs.Constant.TransactionType;
import com.sonnguyen.individual.nhs.Exception.CommitTransactionException;
import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Model.Transfer;
import com.sonnguyen.individual.nhs.Service.IService.ITransferService;
import com.sonnguyen.individual.nhs.dao.GeneralDAO;
import com.sonnguyen.individual.nhs.dao.Idao.IAccountDAO;
import com.sonnguyen.individual.nhs.dao.Idao.ITransactionDAO;
import com.sonnguyen.individual.nhs.dao.Idao.ITransferDAO;
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
    ITransferDAO transferDAO;
    @Inject
    ITransactionDAO transactionDAO;
    @Inject
    IAccountDAO accountDAO;
    Logger logger=Logger.getLogger(this.getClass());



    @Override
    public Transfer startTransfer(Connection connection, Transfer transfer) throws SQLException {
        accountDAO.updateBalanceByAccountId(connection,transfer.getTransaction().getAccountId(),transfer.getTransaction().getAmount().negate());
        accountDAO.updateBalanceByAccountId(connection,transfer.getAccountId(),transfer.getTransaction().getAmount());
        Integer transaction=transactionDAO.createTransaction(connection,transfer.getTransaction());
        transfer.setTransactionId(transaction);
        transfer=transferDAO.startTransfer(connection,transfer);
        return transfer;
    }




    @Override
    public String init(Transfer send) {
        return GeneralDAO.createTransactional(connection -> init(connection,send));
    }



    @Override
    public Transfer transferCommit(String transactionRefNumber) {
        return GeneralDAO.createTransactional(connection -> transferCommit(connection,transactionRefNumber));
    }

    @Override
    public Transfer transferCommit(Connection connection, String transactionRefNumber) throws SQLException {
        List<Transaction> transactions=transactionDAO.findAllByRefNumber(transactionRefNumber);
        Transaction transferTransaction=null;
        for(Transaction transaction:transactions){
            if(transaction.getTransactionType()==TransactionType.TRANSFER.value){
                transferTransaction=transaction;
                break;
            }
        }
        if(transferTransaction==null) throw new CommitTransactionException("Unable to commit transaction! Transference transaction could not be found");
        Transfer transfer=transferDAO.findByTransactionId(connection,transferTransaction.getId());
        transfer.setTransaction(transferTransaction);
        accountDAO.updateBalanceByAccountId(connection,transfer.getTransaction().getAccountId(),transfer.getTransaction().getAmount().negate());
        accountDAO.updateBalanceByAccountId(connection,transfer.getAccountId(),transfer.getTransaction().getAmount());
        transactionDAO.updateStatus(connection,transactions.get(0).getId(),TransactionStatus.SUCCESS.value);
        transactionDAO.updateStatus(connection,transactions.get(1).getId(),TransactionStatus.SUCCESS.value);
        return transfer;
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
        Integer transferTransactionId=transactionDAO.createTransaction(connection,send.getTransaction());
        send.setTransactionId(transferTransactionId);
        transferDAO.startTransfer(connection,send);
        Integer receiveTransactionId=transactionDAO.createTransaction(connection,receiveTransaction);
        receive.setTransactionId(receiveTransactionId);
        transferDAO.startTransfer(connection,receive);
        return referenceNumber;
    }

}
