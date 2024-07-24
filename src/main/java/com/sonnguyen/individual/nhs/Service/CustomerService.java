package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;
import com.sonnguyen.individual.nhs.Service.IService.ILoginService;
import com.sonnguyen.individual.nhs.dao.Idao.ICustomerDAO;

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

    @Override
    public boolean isValid(String email, String phoneNumber, String social_security_number) {
        try {
            return customerRepository.isValid(email,phoneNumber,social_security_number);
        } catch (SQLException e) {
            return false;
        }
    }

}
