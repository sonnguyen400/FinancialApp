package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Customer;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface ICustomerService {
    Collection<Customer> findAllByAccountNumber(String accountNumber);
    Customer findById(Integer customerId);
    boolean isValid(String email,String phoneNumber,String social_security_number);
}
