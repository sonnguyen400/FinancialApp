package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.dao.Idao.ICustomerDAO;
import com.sonnguyen.individual.nhs.service.iService.IAccountService;
import com.sonnguyen.individual.nhs.service.iService.ICustomerService;
import com.sonnguyen.individual.nhs.service.iService.ILoginService;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Model
@EJB
public class CustomerService implements ICustomerService {
    @Inject
    private ICustomerDAO customerRepository;
    @Inject
    private IAccountService accountService;
    @Inject
    private ILoginService loginService;



    public Collection<Customer> findAllByAccountId(Integer accountId)  {
        return customerRepository.findAllByAccountId(accountId);
    }

    @Override
    public Optional<Customer> findByAccountId(Integer accountId) {
        List<Customer> customers = (List<Customer>) customerRepository.findAllByAccountId(accountId);
        return Optional.of(customers.get(0));
    }

    @Override
    public Collection<Customer> findAllByAccountNumber(String accountNumber) {
        try {
            return customerRepository.findAllByAccountNumber(accountNumber);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Customer findById(Integer customerId) {
        try {
            return customerRepository.findById(customerId).get();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
