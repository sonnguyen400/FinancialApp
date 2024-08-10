package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.impl.CustomerDAOImpl;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Model
public class CustomerService implements ICustomerService {
    @Inject
    private CustomerDAOImpl customerDAO;


    @Override
    public Collection<Customer> findAllByAccountNumber(String accountNumber) {
        try {
            return customerDAO.findAllByAccountNumber(accountNumber);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Optional<Customer> findById(Integer customerId) {
        return customerDAO.findById(customerId);
    }

    @Override
    public boolean isValid(String email, String phoneNumber, String social_security_number) {
        try {
            return customerDAO.isValid(email,phoneNumber,social_security_number);
        } catch (SQLException e) {
            return false;
        }
    }
    @Override
    public Optional<Customer> findByEmail(String email){
        return customerDAO.findByEmail(email);
    }
}
