package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.TransactionStatus;
import com.sonnguyen.individual.nhs.constant.TransactionType;
import com.sonnguyen.individual.nhs.dao.core.DBTransaction;
import com.sonnguyen.individual.nhs.dao.idao.IAccountDAO;
import com.sonnguyen.individual.nhs.dao.idao.ISavingDAO;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.SavingsInfo;
import com.sonnguyen.individual.nhs.model.Transaction;
import com.sonnguyen.individual.nhs.model.Transfer;
import com.sonnguyen.individual.nhs.service.iservice.ISavingsInfoService;
import org.apache.velocity.exception.ResourceNotFoundException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Model
public class SavingInfoService implements ISavingsInfoService {
    @Inject
    ISavingDAO savingDAO;
    @Inject
    IAccountDAO accountDAOImp;
    @Inject
    DBTransaction dbTransaction;
    @Inject
    TransferTransactionService transferTransactionService;



    @Override
    public Optional<SavingsInfo> findByAccountId(int accountId) {
        try {
            return savingDAO.findByAccountId(accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<SavingsInfo> findById(int accountId) {
        return savingDAO.findById(accountId);
    }

    @Override
    public Optional<SavingsInfo> completeSavings(SavingsInfo savingsInfo, int beneficiaryAccountId){
        Account linkedAccount=accountDAOImp.findById(savingsInfo.getAccountId()).orElseThrow(()->new ResourceNotFoundException("Account Not Found"));
        Account branchAccount=accountDAOImp.findBranchPrincipalAccount(linkedAccount.getBranchID()).orElseThrow(()->new ResourceNotFoundException("Management Branch isn't found"));
        return  withdrawEntire(savingsInfo,linkedAccount,branchAccount,beneficiaryAccountId);
    }

    @Override
    public List<SavingsInfo> findAllMaturity() {
        return savingDAO.findAllMaturity();
    }

    @Override
    public Optional<SavingsInfo> withdrawEntire(SavingsInfo savingsInfo, Account linkedAccount, Account branchAccount, int beneficiaryAccountId){
        BigDecimal assets=linkedAccount.getBalance();
        BigDecimal profit=savingsInfo.isMature()?calculateProfit(linkedAccount.getBalance(),savingsInfo.getInterestRate().intValue(),savingsInfo.getTerm()):BigDecimal.ZERO;
        Optional<Transaction> transactProfit=Optional.empty();
        if(profit.compareTo(BigDecimal.ZERO) > 0){
            transactProfit=Optional.of(new Transaction()).map(transaction -> {
                transaction.setTransactionType(TransactionType.DISBURSEMENT.value);
                transaction.setAmount(profit);
                transaction.setStatus(TransactionStatus.PENDING.value);
                transaction.setDescription("Profit for savings");
                transaction.setAccountId(branchAccount.getId());
                return transaction;
            });
        }


        Transaction transactInit=new Transaction();
        transactInit.setTransactionType(TransactionType.DISBURSEMENT.value);
        transactInit.setAmount(assets);
        transactInit.setStatus(TransactionStatus.PENDING.value);
        transactInit.setDescription("Disbursement for savings");
        transactInit.setAccountId(branchAccount.getId());

        Transfer transfer=new Transfer();
        transfer.setAccountId(beneficiaryAccountId);
        transfer.setMessage("Disbursement for savings");

        Optional<String> ref=transactProfit.map(transaction -> transferTransactionService.init(transaction,transfer));
        String ref1=transferTransactionService.init(transactInit,transfer);

        return Optional.ofNullable(dbTransaction.startTransaction(SavingsInfo.class, (connection -> {
            ref.ifPresent(s -> transferTransactionService.transferCommit(s));
            transferTransactionService.transferCommit(ref1);
            accountDAOImp.updateAccountStatusByAccountId(connection, linkedAccount.getId(), AccountStatus.CLOSED);
            return savingsInfo;
        })));
    }

    @Override
    public Optional<SavingsInfo> rollOverPrinciple(SavingsInfo savingsInfo, Account linkedAccount, Account branchAccount, int beneficiaryAccountId){
        BigDecimal profit=savingsInfo.isMature()?calculateProfit(linkedAccount.getBalance(),savingsInfo.getInterestRate().intValue(),savingsInfo.getTerm()):BigDecimal.ZERO;
        Transaction transactProfit=new Transaction();
        transactProfit.setTransactionType(TransactionType.DISBURSEMENT.value);
        transactProfit.setAmount(profit);
        transactProfit.setStatus(TransactionStatus.PENDING.value);
        transactProfit.setDescription("Profit for savings");
        transactProfit.setAccountId(branchAccount.getId());
        Transfer transfer=new Transfer();
        transfer.setAccountId(beneficiaryAccountId);
        transfer.setMessage("Profit for savings");
        String ref=transferTransactionService.init(transactProfit,transfer);
        return Optional.ofNullable(dbTransaction.startTransaction(SavingsInfo.class, (connection -> {
            savingDAO.refreshDate(connection,savingsInfo.getId());
            transferTransactionService.transferCommit(ref);
            return savingsInfo;
        })));
    }
    @Override
    public Optional<SavingsInfo> rollOverAll(SavingsInfo savingsInfo, Account linkedAccount, Account branchAccount){
        BigDecimal profit=savingsInfo.isMature()?calculateProfit(linkedAccount.getBalance(),savingsInfo.getInterestRate().intValue(),savingsInfo.getTerm()):BigDecimal.ZERO;
        Transaction transactProfit=new Transaction();
        transactProfit.setTransactionType(TransactionType.DISBURSEMENT.value);
        transactProfit.setAmount(profit);
        transactProfit.setStatus(TransactionStatus.PENDING.value);
        transactProfit.setDescription("Refresh for new period");
        transactProfit.setAccountId(branchAccount.getId());

        Transfer transfer=new Transfer();
        transfer.setAccountId(linkedAccount.getId());
        transfer.setMessage("Refresh for new period");
        String ref=transferTransactionService.init(transactProfit,transfer);
        return Optional.ofNullable(dbTransaction.startTransaction(SavingsInfo.class, (connection -> {
            savingDAO.refreshDate(connection,savingsInfo.getId());
            transferTransactionService.transferCommit(ref);
            return savingsInfo;
        })));
    }



    /**
     * @param original is initial money
     * @param interest ( eg: 3,5,10)
     * @param term (unit: month)
     * @return profit
     */
    public static BigDecimal calculateProfit(BigDecimal original,int interest,int term){
        return original.multiply(BigDecimal.valueOf(interest)).divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(term/12));
    }

}
