package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Customer;

import java.sql.SQLException;
import java.util.Collection;

public interface ICustomerRepository extends AbstractRepository<Customer,Integer> {
    public Customer insert(Customer object) throws SQLException;
    public Collection<Customer> findAllByAccountId(int accountId) throws SQLException;
}
