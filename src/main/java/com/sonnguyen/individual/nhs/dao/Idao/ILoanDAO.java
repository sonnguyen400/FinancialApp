package com.sonnguyen.individual.nhs.dao.Idao;

import com.sonnguyen.individual.nhs.Model.Loan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface ILoanDAO extends AbstractDAO<Loan,Integer> {
    Collection<Loan> findAllByCustomerId(Integer customerId);
    Collection<Loan> findAllByStatus(Integer status);
    Integer updateStatusById(Integer id,Integer status) throws SQLException;

    Integer updateStatusById(Connection connection,Integer id, Integer status) throws SQLException;
}
