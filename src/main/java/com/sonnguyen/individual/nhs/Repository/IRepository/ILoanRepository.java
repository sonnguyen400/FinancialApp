package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Loan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface ILoanRepository extends AbstractRepository<Loan,Integer> {
    Collection<Loan> findAllByCustomerId(Integer customerId);
    Collection<Loan> findAllByStatus(String status);
    Integer updateStatusById(Integer id,String status) throws SQLException;

    Integer updateStatusById(Connection connection,Integer id, String status) throws SQLException;
}
