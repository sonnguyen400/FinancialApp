package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Repository.IRepository.ICustomerRepository;

import javax.enterprise.inject.Model;
import java.sql.SQLException;
import java.util.Collection;

@Model
public class CustomerRepository extends Repository<Customer,Integer> implements ICustomerRepository {
    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public Customer insert( Customer object) throws SQLException {
        return super.insert(object);
    }

    @Override
    public Collection<Customer> findAllByAccountId(int accountId) throws SQLException {
        String query="select * from customer where customer.id in (Select customer_id from account_holder where account_holder.account_id=?)";
        return executeSelect(query,accountId);
    }

    @Override
    public Collection<Customer> findAllByAccountNumber(String accountNumber) throws SQLException {
        return null;
    }
}
