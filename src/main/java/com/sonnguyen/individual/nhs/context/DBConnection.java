package com.sonnguyen.individual.nhs.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final Logger log = LoggerFactory.getLogger(DBConnection.class);
    private String driver;
    private String url;
    private String username;
    private String password;

    public DBConnection(String driver, String url, String username, String password){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("Class not found - SQL Driver was lost!",e);
        }
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url,username, password);
        } catch (SQLException e) {
            log.error("Connection can not be established",e);
        }
        return null;
    }

    @Override
    public String toString() {
        return "DBConnection{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
