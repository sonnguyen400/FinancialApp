package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.dao.idao.ILoginCustomerDAO;
import com.sonnguyen.individual.nhs.dao.impl.AccountDAOImp;
import com.sonnguyen.individual.nhs.dao.impl.AccountHolderDAOImpl;
import com.sonnguyen.individual.nhs.dao.impl.CustomerDAOImpl;
import com.sonnguyen.individual.nhs.dao.impl.LoginDAOImp;
import com.sonnguyen.individual.nhs.dao_v2.DBTransaction;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.AccountHolder;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class LoginCustomerDAO  implements ILoginCustomerDAO {
    @Inject
    private LoginDAOImp loginDAO;
    @Inject
    private CustomerDAOImpl customerDAO;
    @Inject
    private AccountDAOImp repository;
    @Inject
    private AccountHolderDAOImpl accountHolderDAO;
    @Inject
    DBTransaction dbTransaction;

    @Override
    public Customer save(Login login, Customer customer, Account account) {
        return dbTransaction.startTransaction(Customer.class,connection -> {
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
