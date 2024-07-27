package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.model.Customer;

import java.sql.SQLException;
import java.util.Collection;

public interface ICustomerDAO extends AbstractDAO<Customer,Integer> {
    Collection<Customer> findAllByAccountNumber(String accountNumber) throws SQLException;
    boolean isValid(String email, String phone,String socialNumber) throws SQLException;
}
