package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;

public interface ILoginCustomerService {
    Customer save(Login login, Customer customer, Account account);

}
