package com.sonnguyen.individual.nhs.dao_v2;

import java.sql.Connection;

public interface Transactional<T> {
    T startTransaction(Connection connection) throws Exception;
}
