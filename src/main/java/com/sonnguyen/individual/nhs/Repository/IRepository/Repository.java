package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Repository.AbstractRepository;
import com.sonnguyen.individual.nhs.Utils.Console;
import com.sonnguyen.individual.nhs.Utils.EntityMapping;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Repository<T,ID> extends GeneralRepository<T,ID> implements AbstractRepository<T,ID> {
    protected Class<T> entityClass;
    protected Class<ID> idClass;
    public Repository(){
        Type[] types=((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments();
        this.entityClass=(Class<T>)types[0];
        this.idClass=(Class<ID>)types[1];
    }

    @Override
    public List<T> findAll() {
        String query="Select * from " + entityClass.getSimpleName();
        ResultSet resultSet;
        try{
             resultSet=executeSelect(query,entityClass);
             return EntityMapping.mapEntity(resultSet,entityClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }

    @Override
    public Optional<T> findById(ID id) {
        StringBuilder query=new StringBuilder("Select * from ");
        query.append(entityClass.getSimpleName());
        query.append(" where id=");
        query.append(String.valueOf(id));
        ResultSet resultSet=null;
        try{
            resultSet=executeSelect(query.toString(),entityClass);
            if(resultSet.getFetchSize()!=1) {
                Console.err("Returned result greater than 1");
                return Optional.empty();
            }else{
                return Optional.of(EntityMapping.mapEntity(resultSet,entityClass).get(0));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteById( ID id) {
        StringBuilder query=new StringBuilder("Delete * from ");
        query.append(entityClass.getSimpleName());
        query.append(" where id=");
        query.append(String.valueOf(id));
        executeUpdate(query.toString(),entityClass);
    }

    @Override
    public ID insert(T object) {
        executeInsert(object,entityClass);
        return (ID) new Object();
    }

    @Override
    public T update(T object, ID id) {
        List<Field> fields=EntityMapping.getField(entityClass);
        Map<String,String> map=EntityMapping.objectMap(object,entityClass);
        String updateSet=map.entrySet().stream().map(set->{
            return set.getKey()+" "+set.getValue();
        }).collect(Collectors.joining("and"));

        T newObject=findById(id).map(oldobject->{
            try {
                for(Field field:fields){
                    field.setAccessible(true);
                    Object newValue=field.get(object);
                    if(newValue==null) continue;
                    field.set(oldobject,newValue);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            return oldobject;
        }).orElseThrow(()->new RuntimeException("Id not existed"));

        return null;
    }
}
