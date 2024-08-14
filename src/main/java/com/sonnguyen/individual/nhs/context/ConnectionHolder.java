package com.sonnguyen.individual.nhs.context;

import com.sonnguyen.individual.nhs.context.annotation.Value;

import javax.enterprise.inject.Model;
import java.sql.Connection;

@Model
public class ConnectionHolder {
    private static DBConnection connection;
    @Value(name="com.data.mysql.driver")
    private String driver;
    @Value(name = "com.data.mysql.url")
    private String url;
    @Value(name = "com.data.mysql.username")
    private String username;
    @Value(name = "com.data.mysql.password")
    private String password;
    public void getDBConnection() {
        connection=new DBConnection(driver, url, username, password);
    }
    public Connection getConnection() {
        if(connection==null) getDBConnection();
        return connection.getConnection();
    }
}
