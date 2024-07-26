package com.sonnguyen.individual.nhs.dao.idao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public  interface AbstractDAO<T,ID> {
    Class<T> getEntityClass();
    List<T> findAll() throws SQLException;
    Optional<T> findById(ID id) throws SQLException;
    void deleteById(ID id) throws SQLException;
    T save(T object);
    T update(T object,ID id) throws SQLException;
    Integer executeInsert(Connection connection,T object) throws SQLException;

}
