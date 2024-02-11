package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Utils.Console;
import com.sonnguyen.individual.nhs.Utils.EntityMapper;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class GeneralRepository<T,ID> {
    private static final String USERNAME="1Y6wZQbVuhxUp0KQoU1rk2GqQtThEW/r";
    private static final String PASSWORD="Jscs5fU+AFuEnDyOqcE1XA==";
    private static final String ENCYPTOR="HoangBao2003";
    private static final String dbURL="jdbc:mysql://localhost:3306/ap";
    protected static Connection connection;
    private static String decrypt(String encryptor){
        StandardPBEStringEncryptor standardPBEStringEncryptor=new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setPassword(ENCYPTOR);
        return standardPBEStringEncryptor.decrypt(encryptor);
    }
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(dbURL, decrypt(USERNAME), decrypt(PASSWORD));
        }catch (ClassNotFoundException e){
            Console.err("Class not found - SQL Driver was lost!");
            e.printStackTrace();
        }catch (SQLException e){
            Console.err("SQL Exception - Connection could not be established!");
            e.printStackTrace();
        }
    }
    public List<T> executeSelect(String query, Class<T> clazz) throws SQLException {
        Statement statement = null;
        ResultSet resultSet=null;
        List<T> list = null;
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
            list= EntityMapper.mapEntity(resultSet,clazz);
            return list;
        } catch (SQLException e) {
            throw new SQLException(e);
        }finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement!=null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer executeInsert(T object, Class<T> clazz){
        Statement statement=null;
        List<Field> fields=EntityMapper.getField(clazz);
        try{
            Map<String,String> map=EntityMapper.objectMap(object,clazz);
            StringBuilder query=new StringBuilder("insert into ");
            query.append(EntityMapper.getTableName(clazz));
            query.append("(");query.append(String.join(",", map.keySet()));query.append(")");
            query.append(" value(");
            query.append(String.join(",",map.values()));
            query.append(")");
            statement=connection.createStatement();
            if(EntityMapper.isGenerated(EntityMapper.getId(clazz))){
                return statement.executeUpdate(query.toString(),Statement.RETURN_GENERATED_KEYS);
            }else{
                statement.executeUpdate(query.toString());
                return null;
            }
        }catch (SQLException e){
            Console.err("Insert statement was failed");
            e.printStackTrace();
        }finally {
            try {
                if(statement!=null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public void executeDelete(String query,Class<T> tClass){
        Statement statement=null;
        try{
            statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void executeUpdate(String query,Class<T> clazz){
        Statement statement=null;
        try{
            statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
