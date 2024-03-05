package com.sonnguyen.individual.nhs.Repository.IRepository;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T,ID> {
    Class<T> getEntityClass();
    List<T> findAll() throws SQLException;
    Optional<T> findById(ID id) throws SQLException;
    void deleteById(ID id) throws SQLException;
    T insert(T object) throws SQLException;
    T update(T object,ID id) throws SQLException;
}
