package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.dao.core.GeneralDAO;
import com.sonnguyen.individual.nhs.model.Customer;

import java.sql.SQLException;
import java.util.Collection;

public interface ICustomerDAO extends GeneralDAO<Customer,Integer> {
    Collection<Customer> findAllByAccountNumber(String accountNumber) throws SQLException;
    boolean isValid(String email, String phone,String socialNumber) throws SQLException;
}
