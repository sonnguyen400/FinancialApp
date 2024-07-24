package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.dao.Idao.ICustomerDAO;

import javax.enterprise.inject.Model;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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


    @Override
    public boolean isValid(String email, String phone, String socialNumber) throws SQLException {
        String query="SELECT CASE\n" +
                "        WHEN EXISTS (\n" +
                "            SELECT id FROM customer WHERE email=? OR\n" +
                "                                          phone=?  OR\n" +
                "                                          social_security_number=? )\n" +
                "        THEN 0 ELSE 1\n" +
                "        END AS Result";

        return executeSelect(query, Integer.class, email, phone, socialNumber)==1;
    }
}
