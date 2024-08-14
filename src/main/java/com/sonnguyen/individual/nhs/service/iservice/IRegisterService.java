package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;

import java.sql.SQLException;

public interface IRegisterService {
    Customer register(Login login, Customer customer, Account account) throws SQLException;

}
