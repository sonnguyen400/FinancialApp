package com.sonnguyen.individual.nhs.context;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.SQLException;

@Model
@Deprecated
public class ConnectionContext {
    @Inject
    ServletContext servletContext;
    public Connection getDBConnection() throws SQLException {
        DBConnection dbConnection= (DBConnection) servletContext.getAttribute("connection");
        return dbConnection.getConnection();
    }
}
