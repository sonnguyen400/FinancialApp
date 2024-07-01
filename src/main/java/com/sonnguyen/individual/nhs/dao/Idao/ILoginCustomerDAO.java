package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;

public interface ILoginCustomerDAO extends AbstractDAO<Customer,Integer> {
    Customer save(Login login, Customer customer, Account account);
}
