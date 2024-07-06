package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Constant.AccountStatus;
import com.sonnguyen.individual.nhs.Constant.AccountType;
import com.sonnguyen.individual.nhs.Constant.DefaultBrand;
import com.sonnguyen.individual.nhs.Constant.TransactionType;
import com.sonnguyen.individual.nhs.Model.*;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ITransferService;
import com.sonnguyen.individual.nhs.dao.GeneralDAO;
import com.sonnguyen.individual.nhs.dao.Idao.IAccountDAO;
import com.sonnguyen.individual.nhs.dao.Idao.IAccountHolderDAO;
import com.sonnguyen.individual.nhs.dao.Idao.ISavingDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Model
public class AccountService implements IAccountService {
    @Inject
    private IAccountHolderDAO accountHolderDAO;
    @Inject
    private IAccountDAO accountDao;
    @Inject
    private IAccountHolderDAO accountHolderDao;
    @Inject
    private ISavingDAO savingDao;
    @Inject
    private ITransferService transferService;
    public Optional<Account> findByUsername(String username) {
        return accountDao.findByUsername(username);
    }


    @Override
    public Optional<Account> findById(int id) {
        try {
             return accountDao.findById(id).map(account -> {
                 List<AccountHolder> accountHolders=GeneralDAO.createTransactional(connection -> accountHolderDAO.findAllByAccountId(connection,account.getId()));
                 account.setAccountHolders(accountHolders);
                 return account;
             });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        Account savingAccount=GeneralDAO.createTransactional(connection -> {
            //Create saving account
            Account account=new Account();
            account.setBalance(BigDecimal.ZERO);
            account.setAccountNumber(UUID.randomUUID().toString().substring(0,8));
            account.setAccountType(AccountType.SAVINGS.value);
            account.setBranchID(DefaultBrand.ID.value);
            account.setStatus(AccountStatus.PENDING.value);
            Integer accountId=accountDao.executeInsert(connection,account);
                //Create account holder
            AccountHolder holder=new AccountHolder(accountId, customerId);
            accountHolderDao.executeInsert(connection,holder);
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
        GeneralDAO.createTransactional(connection -> {
            transferService.transferCommit(connection,refNumber);
            return accountDao.updateAccountStatusByAccountId(connection,savingAccount.getId(),AccountStatus.OPEN);
        });
    }



    @Override
    public List<Account> findByStatusAndTypeAndCustomerId(AccountStatus accountStatus,AccountType type,Integer customerId) {
        return accountDao.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.SAVINGS,customerId );
    }

    @Override
    public Account findDefaultAccountByCustomerId(Integer customerId) {
        return accountDao.findDefaultAccountByCustomerId(customerId);
    }

    @Override
    public List<Account> findPrincipleByCustomerId(Integer customerId) {
        return accountDao.findByCustomerIdAndType(customerId,AccountType.PRINCIPAL);
    }


}
