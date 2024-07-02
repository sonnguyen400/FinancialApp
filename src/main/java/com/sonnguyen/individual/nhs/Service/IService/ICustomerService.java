package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Model.Customer;

import javax.ejb.Remote;
import java.util.Collection;
import java.util.Optional;

@Remote
public interface ICustomerService {
    Collection<Customer> findAllByAccountId(Integer accountId) ;
    Optional<Customer> findByAccountId(Integer accountId);
    Collection<Customer> findAllByAccountNumber(String accountNumber);
    Customer findById(Integer customerId);
}
