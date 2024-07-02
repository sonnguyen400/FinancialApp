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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Model
public class AccountService implements IAccountService {
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
            return accountDao.findById(id);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Account> findSavingsAccountsByCustomerId(Integer customerId) {
        return accountDao.findSavingAccountByCustomerId(customerId);
    }

    @Override
    public Account findPrincipalAccountByCustomerId(Integer customerId) {
        return accountDao.findByCustomerIdAndType(customerId, AccountType.PRINCIPAL).get(0);
    }

    @Override
    public Optional<Account> findAccountByAccountNumber(String username) {
        return accountDao.findAccountByAccountNumber(username);
    }

    @Override
    public BigDecimal updateBalanceByAccountId(Connection connection,Integer accountId, BigDecimal value) throws SQLException {
        return accountDao.updateBalanceByAccountId(connection,accountId, value);
    }

    @Override
    public Collection<Account> findAllByCustomerId(Integer customerId) {
        return accountDao.findAllByCustomerId(customerId);
    }


    @Override
    public Account createSavingsAccount(Integer customerId, SavingsInfo savingsInfor) {
        Account newSavingAccount=GeneralDAO.createTransactional(connection -> {
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

            String refNumber=transferService.init(connection,transfer);
            transferService.transferCommit(connection,refNumber);
            accountDao.updateAccountStatusByAccountId(connection,account.getId(),AccountStatus.OPEN);
            return account;
        });
        return newSavingAccount;
    }



    @Override
    public Optional<Account> findOpeningSavingByCustomerId(Integer customerId) {
        List<Account> accounts=accountDao.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN, AccountType.SAVINGS,customerId );
        if(accounts.size()==1) return Optional.of(accounts.get(0));
        else return Optional.empty();
    }

    @Override
    public Account findDefaultAccountByCustomerId(Integer customerId) {
        return accountDao.findDefaultAccountByCustomerId(customerId);
    }




}
