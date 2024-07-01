package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Loan;
import com.sonnguyen.individual.nhs.dao.Idao.ILoanDAO;
import com.sonnguyen.individual.nhs.Utils.EntityMapper;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Model
public class LoanDAO extends DAO<Loan,Integer> implements ILoanDAO {
    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public Collection<Loan> findAllByCustomerId(Integer customerId) {
        Connection connection=getConnection();
        String query = "SELECT * from Loan where customer_id=? order by create_at";
        List<Loan> result;
        try {
            result=executeSelect(connection,query,customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Collection<Loan> findAllByStatus(String status) {
        Connection connection=getConnection();
        String query = "select * from loan join customer on loan.customer_id=customer.id where status=?";
        ResultSet resultSet=null;
        Collection<Loan> loans= new ArrayList<>(Collections.emptyList());
        if(connection!=null){
            try(PreparedStatement preparedStatement=connection.prepareStatement(query)) {
                preparedStatement.setString(1,status);
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    Loan loan=EntityMapper.map(resultSet, Loan.class);
                    Customer customer=EntityMapper.map(resultSet, Customer.class);
                    if(loan!=null&&customer!=null) {
                        customer.setId(loan.getCustomerId());
                        loan.setCustomer(customer);
                    }
                    loans.add(loan);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally {
                try {
                    connection.close();
                    if(resultSet!=null) resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return loans;
    }

    @Override
    public Integer updateStatusById(Integer id,String status) throws SQLException {
        String query = "update loan set status=? where id=?";
        return executeUpdate(query,status,id);
    }

    @Override
    public Integer updateStatusById(Connection connection, Integer id, String status) throws SQLException {
        String query = "update loan set status=? where id=?";
        return executeUpdate(connection,query,status,id);
    }


}