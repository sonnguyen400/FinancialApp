package com.sonnguyen.individual.nhs.dao_v2;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetMapper {
    public static Object map(ResultSet resultSet,int columnIndex) throws SQLException {
        return resultSet.getObject(columnIndex);
    }
}
