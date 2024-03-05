package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Exception.FailureTransaction;
import com.sonnguyen.individual.nhs.Utils.Console;
import com.sonnguyen.individual.nhs.Utils.EntityMapper;
import com.sonnguyen.individual.nhs.Utils.Transactional;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
public class GeneralRepository<T,ID> {
    private static final String USERNAME="1Y6wZQbVuhxUp0KQoU1rk2GqQtThEW/r";
    private static final String PASSWORD="Jscs5fU+AFuEnDyOqcE1XA==";
    private static final String ENCYPTOR="HoangBao2003";
    private static final String dbURL="jdbc:mysql://localhost:3306/nhsbank";
    protected static Connection connection;
    private static String decrypt(String encryptor){
        StandardPBEStringEncryptor standardPBEStringEncryptor=new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setPassword(ENCYPTOR);
        return standardPBEStringEncryptor.decrypt(encryptor);
    }
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(dbURL,"root", "");
        }catch (ClassNotFoundException e){
            Console.err("Class not found - SQL Driver was lost!");
            e.printStackTrace();
        }catch (SQLException e){
            Console.err("SQL Exception - Connection could not be established!");

        }
    }
    public static void transactionStart(Transactional transactional) throws FailureTransaction{
        try {
            connection.setAutoCommit(false);
            transactional.startTransaction();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                throw new FailureTransaction();
            }
            e.printStackTrace();
            throw new FailureTransaction();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<T> executeSelect(String query, Class<T> clazz,Object ...params) throws SQLException {
        ResultSet resultSet=null;
        List<T> list = null;
        try (PreparedStatement statement=connection.prepareStatement(query)) {
            setPreparedStatement(statement,List.of(params));
            resultSet=statement.executeQuery();
            list= EntityMapper.mapEntity(resultSet,clazz);
        } finally {
            if(resultSet != null) resultSet.close();
        }
        return list;
    }

    public Integer executeInsert(T object, Class<T> clazz) throws SQLException {
        PreparedStatement statement=null;
        List<Field> fields=EntityMapper.getField(clazz);
        ResultSet resultSet=null;
        try{
            Map<String,Object> map=EntityMapper.objectMap(object,clazz);
            StringBuilder query=new StringBuilder("insert into ");
            query.append(EntityMapper.getTableName(clazz));
            query.append("(");query.append(String.join(",", map.keySet()));query.append(")");
            query.append(" value(");
            query.append(String.join(",", Collections.nCopies(map.keySet().size(),"?")));
            query.append(")");
            if(EntityMapper.isGenerated(EntityMapper.getId(clazz))){
                statement=connection.prepareStatement(query.toString(),Statement.RETURN_GENERATED_KEYS);
                setPreparedStatement(statement,map.values());
                statement.executeUpdate();
                resultSet=statement.getGeneratedKeys();
                if(resultSet.next()&&resultSet.getInt(1)>=1) return resultSet.getInt(1);
            }else{
               statement=connection.prepareStatement(query.toString());
               setPreparedStatement(statement,map.values());
               return statement.executeUpdate();
            }
        }finally {
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
        }
        return null;
    }
    public int executeUpdate(String query,Object ...param) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < param.length; i++) {
                statement.setObject(i + 1, param[i]);
            }
            return statement.executeUpdate(query);
        }
    }

    public void setPreparedStatement(PreparedStatement preparedStatement, Collection<Object> objects){
        int[] i= new int[]{1};
        objects.forEach(value->{
            try {
                if(value instanceof String) preparedStatement.setString(i[0], (String) value);
                else if(value instanceof Date) preparedStatement.setDate(i[0],  (Date) value);
                else preparedStatement.setObject(i[0],value);
                i[0] = i[0]+1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
