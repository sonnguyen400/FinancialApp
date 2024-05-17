package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Constant.AccountStatus;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.AccountHolder;
import com.sonnguyen.individual.nhs.Model.SavingsInfor;
import com.sonnguyen.individual.nhs.Repository.GeneralRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountHolderRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.IAccountRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.ISavingRepository;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Constant.AccountType;

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
            Account account=savingsInfor.getAccount();
            account.setAccountNumber(UUID.randomUUID().toString().substring(0,8));
            account.setAccountType(AccountType.SAVINGS.value);
            account.setBranchID(1);
            account.setStatus(AccountStatus.OPEN.value);
            Integer accountId=accountRepository.executeInsert(connection,account);
            AccountHolder holder=new AccountHolder(accountId, customerId,AccountType.SAVINGS.value);
            accountHolderRepository.executeInsert(connection,holder);
            savingsInfor.setAccountId(accountId);
            savingRepository.executeInsert(connection,savingsInfor);
            return accountRepository.executeInsert(connection,account);
        });
    }


}
