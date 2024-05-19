package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Constant.AccountStatus;
import com.sonnguyen.individual.nhs.Constant.TransactionType;
import com.sonnguyen.individual.nhs.Model.*;
import com.sonnguyen.individual.nhs.Repository.GeneralRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountHolderRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.ISavingRepository;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Constant.AccountType;
import com.sonnguyen.individual.nhs.Service.IService.ITransferService;

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
    private IAccountRepository accountRepository;
    @Inject
    private IAccountHolderRepository accountHolderRepository;
    @Inject
    private ISavingRepository savingRepository;
    @Inject
    private ITransferService transferService;
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }


    @Override
    public Optional<Account> findSavingsAccountsByCustomerId(Integer customerId) {
        return accountRepository.findSavingAccountByCustomerId(customerId);
    }

    @Override
    public Account findPrincipalAccountByCustomerId(Integer customerId) {
        return accountRepository.findByCustomerIdAndType(customerId, AccountType.PRINCIPAL).get(0);
    }

    @Override
    public Optional<Account> findAccountByAccountNumber(String username) {
        return accountRepository.findAccountByAccountNumber(username);
    }

    @Override
    public BigDecimal updateBalanceByAccountId(Connection connection,Integer accountId, BigDecimal value) throws SQLException {
        return accountRepository.updateBalanceByAccountId(connection,accountId, value);
    }

    @Override
    public Collection<Account> findAllByCustomerId(Integer customerId) {
        return accountRepository.findAllByCustomerId(customerId);
    }


    @Override
    public Account createSavingsAccount(Integer customerId, SavingsInfor savingsInfor) {
        return GeneralRepository.createTransactional(connection -> {
            //Create saving account
            Account account=new Account();
            account.setBalance(BigDecimal.ZERO);
            account.setAccountNumber(UUID.randomUUID().toString().substring(0,8));
            account.setAccountType(AccountType.SAVINGS.value);
            account.setBranchID(1);
            account.setAccountType(AccountType.SAVINGS.value);
            account.setStatus(AccountStatus.OPEN.value);
            Integer accountId=accountRepository.executeInsert(connection,account);
                //Create account holder
            AccountHolder holder=new AccountHolder(accountId, customerId);
            accountHolderRepository.executeInsert(connection,holder);
                // Create saving information
            savingsInfor.setAccountId(accountId);
            Integer savingAccId=savingRepository.executeInsert(connection,savingsInfor);
            savingsInfor.setId(savingAccId);

            //Transfer from source to saving account
            Transaction transaction=new Transaction();
            transaction.setTransactionType(TransactionType.TRANSFER.value);
            transaction.setDescription("Transfer for saving account");
            transaction.setValue(savingsInfor.getAmount());
            transaction.setAccountId(savingsInfor.getSourceAccount());
            Transfer transfer=new Transfer();
            transfer.setTransaction(transaction);
            transfer.setAccountId(savingsInfor.getAccountId());
            transfer.setMessage("Transfer for saving account");
            transferService.startTransfer(connection,transfer);
            return account;
        });
    }

    @Override
    public Optional<Account> findOpeningSavingByCustomerId(Integer customerId) {
        List<Account> accounts=accountRepository.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN.value, AccountType.SAVINGS.value,customerId );
        if(accounts.size()==1) return Optional.of(accounts.get(0));
        else return Optional.empty();
    }

    @Override
    public Account findDefaultAccountByCustomerId(Integer customerId) {
        return accountRepository.findDefaultAccountByCustomerId(customerId);
    }


}
