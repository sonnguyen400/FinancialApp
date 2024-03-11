package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Model.Customer;

import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.Collection;

@Remote
public interface ICustomerService {
    Customer insert(Customer customer) throws SQLException ;
    Collection<Customer> findAllByAccountId(Integer accountId) throws SQLException;
    Collection<Customer> findAllByAccountNumber(String accountNumber) throws SQLException;
}
