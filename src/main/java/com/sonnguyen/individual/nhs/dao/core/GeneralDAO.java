package com.sonnguyen.individual.nhs.dao.core;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface GeneralDAO<T,ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    Optional<T> findById(Connection connection, ID id);
    ID executeInsert(Connection connection,T object);
    ID executeInsert(T object);
    List<T> executeSelect(Connection connection,String query,Object ...params);
    List<T> executeSelect(String query,Object ...params);
    int deleteById(Connection connection,int id);
}
