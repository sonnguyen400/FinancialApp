package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;

public interface ILoginCustomerRepository extends AbstractRepository<Customer,Integer> {
    Customer save(Login login, Customer customer, Account account);
}
