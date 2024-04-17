package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Loan;
import com.sonnguyen.individual.nhs.Repository.IRepository.ILoanRepository;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Model
public class LoanRepository extends Repository<Loan,Integer> implements ILoanRepository {
    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public Collection<Loan> findAllByCustomerId(Integer customerId) {
        Connection connection=getConnection();
        String query = "SELECT * from Loan where customer_id=?";
        List<Loan> result;
        try {
            result=executeSelect(connection,query,customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
