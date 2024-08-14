package com.sonnguyen.individual.nhs.dao.core;

import com.sonnguyen.individual.nhs.context.ApplicationConfig;
import com.sonnguyen.individual.nhs.context.ConnectionHolder;
import com.sonnguyen.individual.nhs.context.annotation.Value;
import com.sonnguyen.individual.nhs.utils.EntityMapper;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Map prepare statements and params
 * Directly execute query
 */
public class PrincipalDAO {
    @Value(name = "application.debug")
    protected Boolean DEBUG_MODE;
    @Inject
    ConnectionHolder connectionHolder;
    @Inject
    ApplicationConfig applicationConfig;
    public Connection getConnection() {
        return connectionHolder.getConnection();
    }

    public ResultSet executeQuery(PreparedStatement preparedStatement,Object ...params) throws SQLException {
        QueryBuilder.setStatementParams(preparedStatement,params);
        return preparedStatement.executeQuery();
    }



    /**
     * IdClass is the type of ID
     * Only for insert statement
     * @return generated id
     */
    public <T> T executeInsert(PreparedStatement preparedStatement,Class<T> IdClass,Object ...params){
        try {
            QueryBuilder.setStatementParams(preparedStatement,params);
            preparedStatement.executeUpdate();
            return EntityMapper.mapObject(IdClass,preparedStatement.getGeneratedKeys());
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Only for insert statement
     * @return generated updated row
     */
    public int executeUpdate(PreparedStatement preparedStatement,Object ...params){
        try {
            QueryBuilder.setStatementParams(preparedStatement,params);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
