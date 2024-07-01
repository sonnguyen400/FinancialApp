package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.dao.Idao.ICustomerDAO;

import javax.enterprise.inject.Model;
import java.sql.SQLException;
import java.util.Collection;

@Model
public class CustomerDAO extends DAO<Customer,Integer> implements ICustomerDAO {
    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }



    @Override
    public Collection<Customer> findAllByAccountId(int accountId){
        String query="select * from customer where customer.id in (Select customer_id from account_holder where account_holder.account_id=?)";
        try {
            return find(query,accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Customer> findAllByAccountNumber(String accountNumber) throws SQLException {
        StringBuilder query=new StringBuilder("select * from customer where id in");
        query.append("(Select customer_id from account_holder where account_id in(");
        query.append("select id from account where account_number=?))");
        return find(query.toString(),accountNumber);
    }
}
