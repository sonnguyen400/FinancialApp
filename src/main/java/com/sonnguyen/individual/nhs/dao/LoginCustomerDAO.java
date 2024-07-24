package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Constant.AccountType;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.AccountHolder;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.dao.Idao.*;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

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
