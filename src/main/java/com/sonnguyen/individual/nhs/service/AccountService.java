package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.constant.AccountStatus;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.constant.DefaultBrand;
import com.sonnguyen.individual.nhs.constant.TransactionType;
import com.sonnguyen.individual.nhs.dao.impl.AccountDAOImp;
import com.sonnguyen.individual.nhs.dao.impl.AccountHolderDAOImpl;
import com.sonnguyen.individual.nhs.dao.impl.SavingDAOImp;
import com.sonnguyen.individual.nhs.dao_v2.DBTransaction;
import com.sonnguyen.individual.nhs.model.*;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ITransferService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Model
public class AccountService implements IAccountService {
    @Inject
    private AccountHolderDAOImpl accountHolderDAO;
    @Inject
    private AccountDAOImp accountDao;
    @Inject
    private DBTransaction dbTransaction;
    @Inject
    private SavingDAOImp savingDao;
    @Inject
    private ITransferService transferService;

    public Optional<Account> findBranchPrincipalAccount(int branchId){
        return accountDao.findBranchPrincipalAccount(branchId);
    }


    @Override
    public Optional<Account> findById(int id) {
        return accountDao.findById(id).map(account -> {
            List<AccountHolder> accountHolders=dbTransaction.startTransaction(List.class,
                    connection -> accountHolderDAO.findAllByAccountId(connection,account.getId()));
            account.setAccountHolders(accountHolders);
            return account;
        });
    }

    @Override
    public Optional<Account> findByAccountNumber(String username) {
        return accountDao.findAccountByAccountNumber(username);
    }

    @Override
    public Collection<Account> findAllByCustomerId(Integer customerId) {
        return accountDao.findAllByCustomerId(customerId);
    }

    @Override
    public void createSavingsAccount(Integer customerId, SavingsInfo savingsInfor) {
        Account savingAccount=dbTransaction.startTransaction(Account.class,connection -> {
            //Create saving account
            Account account=new Account();
            account.setBalance(BigDecimal.ZERO);
            account.setAccountNumber(UUID.randomUUID().toString().substring(0,8));
            account.setAccountType(AccountType.SAVINGS.value);
            account.setBranchID(DefaultBrand.ID.value);
            account.setStatus(AccountStatus.PENDING.value);
            account.setOpenDate(Date.valueOf(LocalDate.now()));
            Integer accountId=accountDao.executeInsert(connection,account);
            //Create account holder
            AccountHolder holder=new AccountHolder(accountId, customerId);
            accountHolderDAO.executeInsert(connection,holder);
            // Create saving information
            savingsInfor.setAccountId(accountId);
            Integer savingAccId=savingDao.executeInsert(connection,savingsInfor);
            account.setId(accountId);
            return account;
        });
        //Transfer from source to saving account
        Transaction transaction=new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER.value);
        transaction.setDescription("Transfer amount into saving account");
        transaction.setAmount(savingsInfor.getAmount());
        transaction.setAccountId(savingsInfor.getSourceAccount());

        Transfer transfer=new Transfer();
        transfer.setTransaction(transaction);
        transfer.setAccountId(savingsInfor.getAccountId());
        transfer.setMessage("Transfer into for saving account");
        transfer.setTransaction(transaction);

        String refNumber=transferService.init(transfer);
        dbTransaction.startTransaction(Transfer.class,connection -> {
            accountDao.updateAccountStatusByAccountId(connection,savingAccount.getId(),AccountStatus.OPEN);
            return transferService.transferCommit(connection,refNumber);
        });
    }



    @Override
    public List<Account> findByStatusAndTypeAndCustomerId(AccountStatus accountStatus,AccountType type,Integer customerId) {
        return accountDao.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.SAVINGS,customerId );
    }

    @Override
    public Account findDefaultAccountByCustomerId(Integer customerId) {
        return accountDao.findDefaultAccountByCustomerId(customerId).orElse(null);
    }

    @Override
    public List<Account> findPrincipleByCustomerId(Integer customerId) {
        return accountDao.findByCustomerIdAndType(customerId,AccountType.PRINCIPAL);
    }


}