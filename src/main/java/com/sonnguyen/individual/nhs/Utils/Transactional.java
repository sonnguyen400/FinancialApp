package com.sonnguyen.individual.nhs.Utils;

import java.sql.Connection;

public interface Transactional {
    Object startTransaction(Connection connection) throws Exception;
}
