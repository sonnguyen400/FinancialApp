package com.sonnguyen.individual.nhs.dao.core;

import com.sonnguyen.individual.nhs.utils.EntityMapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CRUDDao extends PrincipalDAO{

    public <T> List<T> executeSelect(PreparedStatement preparedStatement,Class<T> clazz,Object ...params) {
        try(ResultSet resultSet=executeQuery(preparedStatement,params)){
            return EntityMapper.mapObjects(clazz,resultSet);
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public int executeUpdate(Connection connection,String query,Object ...params) {
        System.out.println(query);
        try(PreparedStatement preparedStatement=connection.prepareStatement(query)) {
            QueryBuilder.setStatementParams(preparedStatement,params);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int executeUpdate(String query,Object ...params) throws SQLException {
        Connection connection=getConnection();
        int result=executeUpdate(connection,query,params);
        connection.close();
        return result;
    }
    public <S> S select(Connection connection,String query,Class<S> clazz,Object ...params) {
        if(applicationConfig.debugEnable()) System.out.println(query);
        S result;
        ResultSet resultSet=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(query)){

            QueryBuilder.setStatementParams(preparedStatement,params);
            resultSet=preparedStatement.executeQuery();
            result=EntityMapper.mapObject(clazz,resultSet);
            resultSet.close();
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public <S> List<S> executeSelect(Connection connection,String query,Class<S> clazz,Object ...params){
        if(applicationConfig.debugEnable()) System.out.println(query);
        try (PreparedStatement ps=connection.prepareStatement(query)){
            return executeSelect(ps,clazz,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
