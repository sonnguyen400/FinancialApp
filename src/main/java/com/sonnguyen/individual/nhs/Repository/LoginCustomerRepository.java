package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.AccountHolder;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Repository.IRepository.*;
import com.sonnguyen.individual.nhs.Constant.AccountType;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class LoginCustomerRepository extends Repository<Customer,Integer> implements ILoginCustomerRepository {
    @Inject
    private ILoginRepository loginRepository;
    @Inject
    private ICustomerRepository customerRepository;
    @Inject
    private IAccountRepository accountRepository;
    @Inject
    private IAccountHolderRepository accountHolderRepository;

    @Override
    public Customer save(Login login, Customer customer, Account account) {
        return  createTransactional(connection -> {
            //Create customer
            Integer customerId=customerRepository.executeInsert(connection,customer);
            //Create login
            login.setCustomerId(customerId);
            Integer loginId=loginRepository.executeInsert(connection,login);
            //Create account
            account.setBranchID(1);
            account.setAccountType(AccountType.PRINCIPAL.value);
            Integer accountId=accountRepository.executeInsert(connection,account);
            customer.setId(customerId);
            login.setId(loginId);
            account.setId(accountId);
            customer.setAccounts(List.of(account));
            customer.setLogin(customer.getLogin());
            AccountHolder accountHolder = new AccountHolder();
            accountHolder.setCustomerID(customerId);
            accountHolder.setAccountID(accountId);
            accountHolderRepository.executeInsert(connection,accountHolder);
            return customer;
        });
    }


}
