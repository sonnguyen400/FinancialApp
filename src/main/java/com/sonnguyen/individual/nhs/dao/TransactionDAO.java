package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Model.Transaction;
import com.sonnguyen.individual.nhs.Model.Transfer;
import com.sonnguyen.individual.nhs.Utils.EntityMapper;
import com.sonnguyen.individual.nhs.dao.Idao.ITransactionDAO;

import javax.enterprise.inject.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Model
public final class TransactionDAO extends DAO<Transaction,Integer> implements ITransactionDAO {
    @Override
    public Class<Transaction> getEntityClass() {
        return Transaction.class;
    }
    @Override
    public Integer createTransaction(Connection connection, Transaction transaction) throws SQLException {
        return executeInsert(connection, transaction,getEntityClass());
    }
    @Override
    public List<Transaction> getHistoryByAccountId(Integer accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<Transaction>();
        ResultSet rs =null;
        String query="select * from transaction\n" +
                "join transfer\n" +
                "on transfer.transaction_id=transaction.id\n" +
                "where transaction.account_id=? or transfer.account_id=?";
        try( Connection connection=getConnection();
             PreparedStatement preparedStatement= Objects.requireNonNull(connection).prepareStatement(query)){
            preparedStatement.setInt(1,accountId);
            preparedStatement.setInt(2,accountId);
            rs=preparedStatement.executeQuery();
            List<Object[]> list=EntityMapper.mapList(rs,Transaction.class,Transfer.class);
            list.forEach(item->{
                Transaction transaction= (Transaction) item[0];
                Transfer transfer= (Transfer) item[1];
                transaction.setTransfer(transfer);
                transactions.add(transaction);
            });

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(rs!=null) rs.close();
        }
        return transactions;
    }
}
