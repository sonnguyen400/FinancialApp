package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.constant.LoanStatus;
import com.sonnguyen.individual.nhs.dao.idao.ILoanDAO;
import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Loan;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.Date;
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
        try(Connection connection=getConnection()){
            String query = "select * from loan where status=?";
            List<Loan> loans = executeSelect(connection,query,status);
            for (Loan loan:loans){
                String subQuery="select * from customer where id=?";
                List<Customer> customer=executeSelect(connection,subQuery,Customer.class,loan.getCustomerId());
                if(customer.size()==1) loan.setCustomer(customer.get(0));
            }
            return loans;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public Integer approveLoanById(Connection connection, Integer id) throws SQLException {
        String query = "update loan set status=?, approval_date=now() where id=?";
        return executeUpdate(connection,query, LoanStatus.APPROVED.value,id);
    }

    /**
     *
     * @param nextPaymentDate
     * @param diff
     * @return Loan with qualified next payment date
     * @Ex nextpayment is 6/8/2020 , you want to find loans that must pay in next 3 days , findAllByNextPaymentDate(new Date(),3)
     * @Ex find loans must pay today, findAllByNextPaymentDate(new Date(),0)
     * @Ex nextpayment is 6/8/2020 , you want to find loans that must pay in next 3 days from 7/8/2021, findAllByNextPaymentDate(new Date("7/8/2021"),3)
     * @throws SQLException
     */
    @Override
    public List<Loan> findAllByNextPaymentDate(Date nextPaymentDate, int diff, boolean nextnewest) throws SQLException {
        int newest=nextnewest?1:0;
        String query="select * from loan when DATEDIFF(DATE_ADD(loan.approval_date, INTERVAL TIMESTAMPDIFF(MONTH ,loan.approval_date, now())+? MONTH),?)=?";
        return executeSelect(query,newest,nextPaymentDate,diff);
    }
    @Override
    public Date findNextPaymentByLoanId(int loanId) {
        try(Connection connection = getConnection()) {
            String query="select DATE_ADD(loan.approval_date, INTERVAL TIMESTAMPDIFF(MONTH ,loan.approval_date, now())+1 MONTH) from loan where approval_date is not null and loan.id=?";
            return select(connection,query,Date.class,loanId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
