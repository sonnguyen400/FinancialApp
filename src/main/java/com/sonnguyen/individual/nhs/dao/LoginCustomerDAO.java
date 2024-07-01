package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.AccountHolder;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.dao.Idao.*;
import com.sonnguyen.individual.nhs.constant.AccountType;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class LoginCustomerDAO extends DAO<Customer,Integer> implements ILoginCustomerDAO {
    @Inject
    private ILoginDAO loginRepository;
    @Inject
    private ICustomerDAO customerRepository;
    @Inject
    private IAccountDAO repository;
    @Inject
    private IAccountHolderDAO accountHolderRepository;

    @Override
    public Customer save(Login login, Customer customer, Account account) {
        return  createTransactional(connection -> {
            Integer customerId=customerRepository.executeInsert(connection,customer);
            login.setCustomerId(customerId);
            Integer loginId=loginRepository.executeInsert(connection,login);
            account.setAccountType(AccountType.PRINCIPAL.value);
            Integer accountId=repository.executeInsert(connection,account);

            customer.setId(customerId);
            login.setId(loginId);
            account.setId(accountId);
            customer.setAccounts(List.of(account));
            customer.setLogin(customer.getLogin());

            AccountHolder accountHolder = new AccountHolder();
            accountHolder.setCustomerID(customerId);
            accountHolder.setAccountID(accountId);
            accountHolder.setDefault(true);
            accountHolderRepository.executeInsert(connection,accountHolder);
            return customer;
        });
    }
}
