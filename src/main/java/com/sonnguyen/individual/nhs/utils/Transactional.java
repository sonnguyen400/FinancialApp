package com.sonnguyen.individual.nhs.utils;

import java.sql.Connection;

public interface Transactional {
    Object startTransaction(Connection connection) throws Exception;
}
