package com.sonnguyen.individual.nhs.dao_v2;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T,ID> extends CRUDDao{
    protected abstract Class<T> getEntityType();
    protected abstract Class<ID> getIdType();
    public List<T> findAll(){
        PreparedStatement ps=null;
        try(Connection connection=getConnection()){
            StringBuilder builder=new StringBuilder("select * from ");
            builder.append(getEntityType().getSimpleName());
            System.out.println(builder);
            ps=connection.prepareStatement(builder.toString());
            return executeSelect(ps,getEntityType());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(ps!=null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Optional<T> findById(int id){
        PreparedStatement ps=null;
        try(Connection connection=getConnection()) {
             return findById(connection,id);
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }finally {
            if(ps!=null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Optional<T> findById(Connection connection,int id){
        PreparedStatement ps=null;
        try {
            StringBuilder builder=new StringBuilder("select *");
            builder.append(" from ");
            builder.append(EntityUtils.getTableName(getEntityType()));
            builder.append(" where id=?");
            System.out.println(builder);
            ps=connection.prepareStatement(builder.toString());
            List<T> result=executeSelect(ps,getEntityType(),id);
            result.forEach(System.out::println);
            if(result.size()==1) return Optional.of(result.get(0));
            else throw new SQLException("Result is not unique");
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }finally {
            if(ps!=null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public ID executeInsert(Connection connection,T object){
        LinkedHashMap<Field,Object> objectMap=EntityUtils.toMap(object,getEntityType());
        String insertQuery=QueryBuilder.insert(getEntityType());
        List<Object> param=new ArrayList<>();
        objectMap.forEach((field,value)->{
            Column column=field.getDeclaredAnnotation(Column.class);
            if(!(field.getDeclaredAnnotation(Transient.class) != null||(column!=null&& !column.insertable()))){
                param.add(value);
            }
        });
        System.out.println(insertQuery);
        try(PreparedStatement preparedStatement=connection.prepareStatement(insertQuery,PreparedStatement.RETURN_GENERATED_KEYS)){
            return executeInsert(preparedStatement,getIdType(), param.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int deleteById(Connection connection,int id){
        PreparedStatement ps=null;
        try {
            StringBuilder builder=new StringBuilder("delete from ");
            builder.append(EntityUtils.getTableName(getEntityType()));
            builder.append(" where id=?");
            System.out.println(builder);
            ps=connection.prepareStatement(builder.toString());
            return executeUpdate(ps,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> executeSelect(Connection connection,String query,Object ...params){
        return executeSelect(connection,query,getEntityType(),params);
    }
    public List<T> executeSelect(String query,Object ...params){
        Connection connection=getConnection();
        List<T> result=executeSelect(connection,query,params);
        try{
            connection.close();
        } catch (SQLException|NullPointerException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
