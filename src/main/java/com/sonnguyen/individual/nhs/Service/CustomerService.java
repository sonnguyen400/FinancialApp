package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Repository.IRepository.ICustomerRepository;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Collection;

@Model
@EJB
public class CustomerService implements ICustomerService {
    @Inject
    private ICustomerRepository customerRepository;
    public Customer insert(Customer customer) throws SQLException {
        return customerRepository.insert(customer);
    }
    public Collection<Customer> findAllByAccountId(Integer accountId) throws SQLException {
        return customerRepository.findAllByAccountId(accountId);
    }

    @Override
    public Collection<Customer> findAllByAccountNumber(String accountNumber) throws SQLException {
        return customerRepository.findAllByAccountNumber(accountNumber);
    }
}
