package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;

public interface ILoginCustomerDAO{
    Customer save(Login login, Customer customer, Account account);
}
