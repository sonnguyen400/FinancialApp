package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.Customer;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface ICustomerDAO extends AbstractDAO<Customer,Integer> {
    Collection<Customer> findAllByAccountId(int accountId);
    Collection<Customer> findAllByAccountNumber(String accountNumber) throws SQLException;
    boolean isValid(String email, String phone,String socialNumber) throws SQLException;
}
