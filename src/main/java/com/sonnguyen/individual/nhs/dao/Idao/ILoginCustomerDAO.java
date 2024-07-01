package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;

public interface ILoginCustomerDAO extends AbstractDAO<Customer,Integer> {
    Customer save(Login login, Customer customer, Account account);
}
