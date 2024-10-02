package com.sonnguyen.individual.nhs.dao.core;

import com.sonnguyen.individual.nhs.context.Value;
import com.sonnguyen.individual.nhs.utils.EntityUtils;

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

public abstract class AbstractDAO<T,ID> extends CRUDDao implements GeneralDAO<T,ID>{
    protected abstract Class<T> getEntityType();
    protected abstract Class<ID> getIdType();
    public List<T> findAll(){
        PreparedStatement ps=null;
        try(Connection connection=getConnection()){
            StringBuilder builder=new StringBuilder("select * from ");
            builder.append(getEntityType().getSimpleName());
            if(applicationConfig.debugEnable()) System.out.println(builder);
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

    @Override
    public Optional<T> findById(ID id){
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
    @Override
    public Optional<T> findById(Connection connection,ID id){
        PreparedStatement ps=null;
        try {
            StringBuilder builder=new StringBuilder("select *");
            builder.append(" from ");
            builder.append(EntityUtils.getTableName(getEntityType()));
            builder.append(" where id=?");
            if(applicationConfig.debugEnable()) System.out.println(builder);
            ps=connection.prepareStatement(builder.toString());
            List<T> result=executeSelect(ps,getEntityType(),id);
            if(result.size()==1) return Optional.of(result.get(0));
            else if(result.size()>1) throw new SQLException("Result is not unique");
            return Optional.empty();
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
    @Override
    public ID executeInsert(Connection connection,T object){
        LinkedHashMap<Field,Object> objectMap=EntityUtils.toMap(object,getEntityType());
        String insertQuery= QueryBuilder.insert(getEntityType());
        if(applicationConfig.debugEnable()) System.out.println(insertQuery);
        List<Object> param=new ArrayList<>();
        objectMap.forEach((field,value)->{
            Column column=field.getDeclaredAnnotation(Column.class);
            if(!(field.getDeclaredAnnotation(Transient.class) != null||(column!=null&& !column.insertable()))){
                param.add(value);
            }
        });
        try(PreparedStatement preparedStatement=connection.prepareStatement(insertQuery,PreparedStatement.RETURN_GENERATED_KEYS)){
            return executeInsert(preparedStatement,getIdType(), param.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ID executeInsert(T object){
        LinkedHashMap<Field,Object> objectMap=EntityUtils.toMap(object,getEntityType());
        String insertQuery= QueryBuilder.insert(getEntityType());
        if(applicationConfig.debugEnable()) System.out.println(insertQuery);
        List<Object> param=new ArrayList<>();
        objectMap.forEach((field,value)->{
            Column column=field.getDeclaredAnnotation(Column.class);
            if(!(field.getDeclaredAnnotation(Transient.class) != null||(column!=null&& !column.insertable()))){
                param.add(value);
            }
        });
        System.out.println(insertQuery);
        try(Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(insertQuery,PreparedStatement.RETURN_GENERATED_KEYS)){
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
            if(applicationConfig.debugEnable()) System.out.println(builder);
            ps=connection.prepareStatement(builder.toString());
            return executeUpdate(ps,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<T> executeSelect(Connection connection,String query,Object ...params){
        return executeSelect(connection,query,getEntityType(),params);
    }
    @Override
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
