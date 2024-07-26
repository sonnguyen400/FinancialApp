package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.AccountHolder;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.dao.idao.*;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class LoginCustomerDAO extends DAO<Customer,Integer> implements ILoginCustomerDAO {
    @Inject
    private ILoginDAO loginDAO;
    @Inject
    private ICustomerDAO customerDAO;
    @Inject
    private IAccountDAO repository;
    @Inject
    private IAccountHolderDAO accountHolderDAO;

    @Override
    public Customer save(Login login, Customer customer, Account account) {
        return  createTransactional(connection -> {
            Integer customerId=customerDAO.executeInsert(connection,customer);
            login.setCustomerId(customerId);
            Integer loginId=loginDAO.executeInsert(connection,login);

            Integer accountId=repository.executeInsert(connection,account);

            customer.setId(customerId);
            login.setId(loginId);
            account.setId(accountId);

            AccountHolder accountHolder = new AccountHolder();
            accountHolder.setCustomerID(customerId);
            accountHolder.setAccountID(accountId);
            accountHolder.setDefault(true);
            accountHolderDAO.executeInsert(connection,accountHolder);
            return customer;
        });
    }
}
