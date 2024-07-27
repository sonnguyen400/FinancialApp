package com.sonnguyen.individual.nhs.dao_v2;

import com.sonnguyen.individual.nhs.context.DBConnection;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.sql.Connection;

@Model
public class ConnectionHolder {
    @Inject
    private ServletContext servletContext;
    public DBConnection getDBConnection() {
        return (DBConnection) servletContext.getAttribute("connection");
    }
    public Connection getConnection() {
        return getDBConnection().getConnection();
    }
}
