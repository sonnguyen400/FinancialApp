package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Customer;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

@Model
public class CustomerDAOImpl extends AbstractDAO<Customer,Integer> {
    @Override
    protected Class<Customer> getEntityType() {
        return Customer.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
    public boolean isValid(String email, String phone, String socialNumber) throws SQLException {
        Connection connection=getConnection();
        String query="SELECT CASE\n" +
                "        WHEN EXISTS (\n" +
                "            SELECT id FROM customer WHERE email=? OR\n" +
                "                                          phone=?  OR\n" +
                "                                          social_security_number=? )\n" +
                "        THEN 0 ELSE 1\n" +
                "        END AS Result";

        boolean result= select(connection,query, Integer.class, email, phone, socialNumber)==1;
        connection.close();
        return result;
    }
    public Collection<Customer> findAllByAccountNumber(String accountNumber) throws SQLException {
        StringBuilder query=new StringBuilder("select * from customer where id in");
        query.append("(Select customer_id from account_holder where account_id in(");
        query.append("select id from account where account_number=?))");
        return executeSelect(query.toString(),accountNumber);
    }
}
