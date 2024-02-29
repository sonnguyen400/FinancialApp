package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Repository.GeneralRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.AbstractRepository;
import com.sonnguyen.individual.nhs.Utils.Console;
import com.sonnguyen.individual.nhs.Utils.EntityMapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Repository<T,ID> extends GeneralRepository<T,ID> implements AbstractRepository<T,ID> {
    protected Class<T> entityClass;
    protected Class<ID> idClass;
    protected Field idField;
    public Repository(){
        Type[] types=((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments();
        this.entityClass=(Class<T>)types[0];
        this.idClass=(Class<ID>)types[1];
        this.idField=EntityMapper.getId(entityClass);
        assert this.idField != null;
        this.idField.setAccessible(true);
    }


    @Override
    public List<T> findAll() {
        String query="Select * from " + EntityMapper.getTableName(entityClass);
        List<T> results;
        try{
             return results=executeSelect(query,entityClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }

    @Override
    public Optional<T> findById(ID id) {
        if (this.idField == null) {
            throw new RuntimeException(entityClass.getSimpleName() +" ID does not exist");
        }
        StringBuilder query=new StringBuilder("Select * from ");
        query.append(EntityMapper.getTableName(entityClass));
        query.append(" where "+idField.getName()+"=");
        query.append(String.valueOf(id));
        try {
            List<T> results = executeSelect(query.toString(),entityClass);
            if(results.size()!=1) {
                Console.err("Returned result greater than 1");
                return Optional.empty();
            }else{
                return Optional.of(results.get(0));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById( ID id) {
        if (this.idField == null) {
            throw new RuntimeException(entityClass.getSimpleName() +" ID does not exist");
        }
        StringBuilder query=new StringBuilder("Delete * from ");
        query.append(entityClass.getSimpleName());
        query.append(" where id=");
        query.append(String.valueOf(id));
        executeUpdate(query.toString(),entityClass);
    }

    @Override
    public T insert(T object) {
        try {
            idField.set(object,executeInsert(object,entityClass));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return object;
    }


    @Override
    public T update(T object, ID id) {
        List<Field> fields= EntityMapper.getField(entityClass);
        Map<String,String> map=EntityMapper.objectMap(object,entityClass);
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
