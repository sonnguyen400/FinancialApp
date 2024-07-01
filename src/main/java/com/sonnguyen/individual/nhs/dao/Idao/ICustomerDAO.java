package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.Customer;

import java.sql.SQLException;
import java.util.Collection;

public interface ICustomerDAO extends AbstractDAO<Customer,Integer> {
    public Collection<Customer> findAllByAccountId(int accountId);
    public Collection<Customer> findAllByAccountNumber(String accountNumber) throws SQLException;
}
