package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.Exception.FailureTransaction;
import com.sonnguyen.individual.nhs.Utils.Console;
import com.sonnguyen.individual.nhs.Utils.EntityMapper;
import com.sonnguyen.individual.nhs.Utils.Transactional;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
public class GeneralDAO<T> {
    private static final String USERNAME="1Y6wZQbVuhxUp0KQoU1rk2GqQtThEW/r";
    private static final String PASSWORD="Jscs5fU+AFuEnDyOqcE1XA==";
    private static final String ENCYPTOR="HoangBao2003";
    private static final String dbURL="jdbc:mysql://localhost:3306/nhsbank";
    private static String decrypt(String encryptor){
        StandardPBEStringEncryptor standardPBEStringEncryptor=new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setPassword(ENCYPTOR);
        return standardPBEStringEncryptor.decrypt(encryptor);
    }
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbURL,"root", "");
        }catch (ClassNotFoundException e){
            Console.err("Class not found - SQL Driver was lost!");
            e.printStackTrace();
        }catch (SQLException e){
            Console.err("SQL Exception - Connection could not be established!");
        }
        return null;
    }


    public static  <T> T createTransactional(Transactional transactional) throws FailureTransaction{
        Connection connection=getConnection();
        T result=null;
        if(connection!=null){
            try {
                connection.setAutoCommit(false);
                result= (T) transactional.startTransaction(connection);
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new FailureTransaction();
                }
                throw new FailureTransaction();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    public List<T> executeSelect(Connection connection,String query, Class<T> clazz,Object ...params) throws SQLException {
        ResultSet resultSet=null;
        List<T> list = null;
        PreparedStatement statement = null;
        try  {
            statement=connection.prepareStatement(query);
            setPreparedStatement(statement,List.of(params));
            resultSet=statement.executeQuery();
            list= EntityMapper.mapEntity(resultSet,clazz);
        } finally {
            if(statement!=null) statement.close();
            if(resultSet != null) resultSet.close();
        }
        return list;
    }
    public <S> S executeSelect(String query,Class<S> tClass,Object ...params) {
        Connection connection=getConnection();
        ResultSet resultSet=null;
        List<T> list = null;
        Object object=null;
        try (PreparedStatement statement=connection.prepareStatement(query)) {
            setPreparedStatement(statement,List.of(params));
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                object= resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                connection.close();
                resultSet.close();
            } catch (SQLException|NullPointerException e) {
                e.printStackTrace();
            }
        }
        return tClass.cast(object) ;
    }


    public Integer executeInsert(Connection connection,T object, Class<T> clazz) throws SQLException {
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
            // Liệu có chống lại SQL Injection
            if(EntityMapper.isGenerated(EntityMapper.getId(clazz))){
                statement=connection.prepareStatement(query.toString(),Statement.RETURN_GENERATED_KEYS);
                setPreparedStatement(statement, map.values());
                statement.executeUpdate();
                resultSet=statement.getGeneratedKeys();
                if(resultSet.next()&&resultSet.getInt(1)>=1) return resultSet.getInt(1);
            }else{
               statement=connection.prepareStatement(query.toString());
               setPreparedStatement(statement, map.values());
               return statement.executeUpdate();
            }
        }finally {
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
        }
        return null;
    }
    public int executeUpdate(Connection connection,String query,Object ...params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        setPreparedStatement(statement,List.of(params));
        return statement.executeUpdate();

    }

    public static void setPreparedStatement(PreparedStatement preparedStatement, Collection<Object> objects){
        int i=1;
        for(Object object:objects){
            try {
                if(object instanceof String) preparedStatement.setString(i, (String) object);
                else if(object instanceof Date) preparedStatement.setDate(i,  (Date) object);
                else if(object instanceof BigDecimal) preparedStatement.setBigDecimal(i,(BigDecimal) object);
                else if(object instanceof Integer) preparedStatement.setInt(i,(Integer) object);
                else if(object instanceof Long) preparedStatement.setLong(i,(Long) object);
                else if(object instanceof Float) preparedStatement.setFloat(i,(Float) object);
                else if(object instanceof Double) preparedStatement.setDouble(i,(Double) object);
                else if(object instanceof Short) preparedStatement.setShort(i,(Short) object);
                else if(object instanceof Byte) preparedStatement.setByte(i,(Byte) object);
                else if(object instanceof Boolean) preparedStatement.setBoolean(i,(Boolean) object);
                else if(object instanceof byte[]) preparedStatement.setBytes(i,(byte[]) object);
                else if(object instanceof Blob) preparedStatement.setBlob(i,(Blob) object);
                else if(object.getClass().isPrimitive()){
                    if(object.getClass().getSimpleName().equals("int"))  preparedStatement.setInt(i, (Integer) object);
                    else if(object.getClass().getSimpleName().equals("long")) preparedStatement.setLong(i,(Long)object);
                    else if(object.getClass().getSimpleName().equals("float")) preparedStatement.setFloat(i,(Float)object);
                    else if(object.getClass().getSimpleName().equals("double")) preparedStatement.setDouble(i,(Double)object);
                    else if(object.getClass().getSimpleName().equals("short")) preparedStatement.setShort(i,(Short)object);
                    else if(object.getClass().getSimpleName().equals("byte")) preparedStatement.setByte(i,(Byte)object);
                    else if(object.getClass().getSimpleName().equals("boolean")) preparedStatement.setBoolean(i,(Boolean)object);
                }
                else preparedStatement.setObject(i,object);

            } catch (SQLException e) {
                System.out.println("Error while mapping prepared statement");
                e.printStackTrace();
            }
            i++;
        }

    }

}
