package com.sonnguyen.individual.nhs.dao_v2;


import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

@Model
public class DBTransaction {
    @Inject
    ConnectionHolder connectionHolder;
    public <T> T startTransaction(Class<T> clazz,Transactional<T> transactional) {
        Connection connection = connectionHolder.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            T result= transactional.startTransaction(connection);
            connection.commit();
            try{
                connection.close();
            } catch (SQLException|NullPointerException e) {
                throw new RuntimeException(e);
            }
            return result;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
    public <T> T startTransaction(Class<T> clazz,Transactional<T> transactional,Transactional<T> fail) {
        Connection connection = connectionHolder.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            T result= transactional.startTransaction(connection);
            connection.commit();
            return result;
        } catch (Exception e) {
            try {
                connection.rollback();
                fail.startTransaction(connection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            try{
                connection.close();
            } catch (SQLException|NullPointerException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
