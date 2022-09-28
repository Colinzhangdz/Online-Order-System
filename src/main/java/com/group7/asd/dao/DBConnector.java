package com.group7.asd.dao;


import com.group7.asd.dao.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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