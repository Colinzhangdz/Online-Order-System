
package com.group7.asd.dao;

import java.sql.Connection;

/**
 *
 * @author Huiming
 */
public abstract class DB {
    protected String URL = "jdbc:mysql://asd-database.mysql.database.azure.com/demo";//replace this string with your jdbc:derby local host url

    protected String dbuser = "asd";//db root user
    protected String dbpass = "bzsg.+CiGd2f"; //db root password
    protected String driver = "com.mysql.jdbc.Driver"; //jdbc client driver - built in with NetBeans
    protected Connection conn; //connection null-instance to be initialized in sub-classes
}