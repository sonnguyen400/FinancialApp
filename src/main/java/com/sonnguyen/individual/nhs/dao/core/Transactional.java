package com.sonnguyen.individual.nhs.dao.core;

import java.sql.Connection;

public interface Transactional<T> {
    T startTransaction(Connection connection) throws Exception;
}
