package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Repository.IRepository.AbstractRepository;
import com.sonnguyen.individual.nhs.Utils.Console;
import com.sonnguyen.individual.nhs.Utils.EntityMapper;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Repository<T, ID> extends GeneralRepository<T, ID> implements AbstractRepository<T, ID> {
//    public void executeQuery(String sql, Object... params) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        for (int i = 0; i < params.length; i++) {
//            preparedStatement.setObject(i + 1, params[i]);
//        }
//
//    }

    @Override
    public Class<T> getEntityClass() {
        return (Class<T>) Object.class;
    }
    @Override
    public List<T> findAll() throws SQLException {
        String query = "Select * from " + EntityMapper.getTableName(getEntityClass());
        List<T> results;
        return results = executeSelect(query, getEntityClass());
    }

    @Override
    public Optional<T> findById(ID id) throws SQLException {
        Field idField = EntityMapper.getId(getEntityClass());
        if (idField == null) {
            throw new RuntimeException(getEntityClass().getSimpleName() + " ID does not exist");
        }
        StringBuilder query = new StringBuilder("Select * from ");
        query.append(EntityMapper.getTableName(getEntityClass()))
                .append(" where ")
                .append(idField.getName())
                .append("=?");
        List<T> results = executeSelect(query.toString(),getEntityClass(),id);
        if(results.size() == 0) {
            Console.err("No results found");
        }
        if (results.size() != 1) {
            Console.err("Returned result greater than 1");
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }

    @Override
    public void deleteById(ID id) throws SQLException {
        Field idField = EntityMapper.getId(getClass());
        if (idField == null) {
            throw new RuntimeException(getClass().getSimpleName() + " ID does not exist");
        }
        StringBuilder query = new StringBuilder("Delete * from ");
        query.append(getEntityClass().getSimpleName());
        query.append(" where id=?");
        executeUpdate(query.toString(),id);
    }

    @Override
    public T insert( T object) throws SQLException {
        Integer id=executeInsert(object, getEntityClass());
        Field idField = EntityMapper.getId(getEntityClass());
        if(idField!=null){
            idField.setAccessible(true);
            try {
                idField.set(object, id);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return object;
    }


    @Override
    public T update(T object, ID id) throws SQLException {
        List<Field> fields = EntityMapper.getField(getEntityClass());
        Map<String, Object> map = EntityMapper.objectMap(object, getEntityClass());
        String updateSet = map.entrySet().stream().map(set -> {
            return set.getKey() + " " + set.getValue();
        }).collect(Collectors.joining("and"));
        T newObject = findById(id).map(oldobject -> {
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object newValue = field.get(object);
                    if (newValue == null) continue;
                    field.set(oldobject, newValue);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return oldobject;
        }).orElseThrow(() -> new RuntimeException("Id not existed"));
        return null;
    }

    public List<T> executeSelect(String query, Object... params) throws SQLException {
        return executeSelect(query, getEntityClass(), params);
    }
}
