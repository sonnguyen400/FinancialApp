package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.model.Loan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface ILoanDAO extends AbstractDAO<Loan,Integer> {
    Collection<Loan> findAllByCustomerId(Integer customerId);
    Collection<Loan> findAllByStatus(String status);
    Integer updateStatusById(Integer id,String status) throws SQLException;

    Integer updateStatusById(Connection connection,Integer id, String status) throws SQLException;
}
