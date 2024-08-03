package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.idao.IPaymentDAO;
import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.model.Payment;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Model
public class PaymentDAOImpl extends AbstractDAO<Payment,Integer> implements IPaymentDAO {
    @Override
    protected Class<Payment> getEntityType() {
        return Payment.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public List<Payment> findAllByLoanId(int loanId) {
        String query = "SELECT * FROM Payment WHERE p.loan_id=?";
        return executeSelect(query,loanId);
    }

    @Override
    public Optional<Payment> findAllByLoanId(int loanId, int month, int year) {
        String query = "SELECT * FROM Payment WHERE p.loan_id=? and MONTH(payment_date)=? and YEAR(payment_date)=? order by payment_date DESC";
        List<Payment> payments=executeSelect(query,loanId,month,year);
        if(payments.isEmpty()) return Optional.empty();
        return Optional.of(payments.get(0));
    }

    @Override
    public int unpaidMonth(int loanID) {
        try(Connection connection=getConnection()){
            String query="Select unpaidMonthlyLoanCount(?)";
            return select(connection,query,Integer.class,loanID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
