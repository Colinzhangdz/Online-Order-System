package com.group7.asd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Huiming
 */
public class DBConnector extends DB {

    public DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(URL, dbuser, dbpass);

    }

    public Connection openConnection() {
        return super.conn;
    }

    public void closeConnection() throws SQLException {
        super.conn.close();
    }
}