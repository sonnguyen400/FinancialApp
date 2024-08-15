package com.sonnguyen.individual.nhs.context;

import com.sonnguyen.individual.nhs.context.annotation.Value;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
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
    public Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
