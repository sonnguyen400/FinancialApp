package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.idao.ILoanDAO;
import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Loan;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
@Model
public class LoanDAOImpl extends AbstractDAO<Loan,Integer> implements ILoanDAO {
    @Override
    protected Class<Loan> getEntityType() {
        return Loan.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
    @Override
    public Collection<Loan> findAllByCustomerId(Integer customerId) {
        String query = "SELECT * from Loan where customer_id=? order by create_at";
        return executeSelect(query,customerId);
    }
    @Override
    public Collection<Loan> findAllByStatus(Integer status) {
        Connection connection = getConnection();
        String query = "select * from loan where status=?";
        List<Loan> loans = executeSelect(connection,query,status);
        for (Loan loan:loans){
            String subQuery="select * from customer where customer_id=?";
            List<Customer> customer=executeSelect(connection,subQuery,Customer.class,loan.getCustomerId());
            if(customer.size()==1) loan.setCustomer(customer.get(0));
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loans;
    }

    @Override
    public Integer updateStatusById(Integer id,Integer status) throws SQLException {
        String query = "update loan set status=? where id=?";
        return executeUpdate(query,status,id);
    }
    @Override
    public Integer updateStatusById(Connection connection, Integer id, Integer status) throws SQLException {
        String query = "update loan set status=? where id=?";
        return executeUpdate(connection,query,status,id);
    }
    @Override
    public Integer executeInsert(Loan loan){
        Connection connection=getConnection();
        Integer id=executeInsert(connection,loan);
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }


}
