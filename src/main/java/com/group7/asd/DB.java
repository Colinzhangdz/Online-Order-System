
package com.group7.asd;

import java.sql.Connection;

public abstract class DB {
    protected String URL = "database.mysql.database.azure.com";//replace this string with your jdbc:derby local host url
    protected String db = "asd";//name of the database
    protected String dbuser = "asd";//db root user
    protected String dbpass = "bzsg.+CiGd2f"; //db root password
    protected String driver = "database.mysql.database.azure.com";
    protected Connection conn; //connection null-instance to be initialized in sub-classes
}