
package com.group7.asd;

import java.sql.*;
import java.util.ArrayList;


public class UserLogDBManager {

    private Statement st;

    public UserLogDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

//create userslog in database
    public void createUserLog(int userId) throws SQLException {
        st.executeUpdate("INSERT INTO USERSLOG(USERID, LOGINDATE, LOGINTIME) VALUES(" + userId + ",CURRENT_DATE,CURRENT_TIMESTAMP )");
    }

//update logout log in userslog in database
    public void addUserLogoutLog(int userId) throws SQLException {
        st.executeUpdate("UPDATE USERSLOG SET LOGOUTDATE=CURRENT_DATE,LOGOUTTIME =  CURRENT_TIMESTAMP WHERE USERLOGID = (SELECT max(USERLOGID) FROM USERSLOG WHERE USERID=" + userId + ") ");
    }

//get userslog from database
    public ArrayList<UserLog> getUserLogByUserId(int userId) throws SQLException {
        ArrayList<UserLog> userLogs = new ArrayList<>();
        ResultSet rst = st.executeQuery("SELECT * FROM USERSLOG WHERE USERID=" + userId);
        while (rst.next()) {
            userLogs.add(new UserLog(rst.getInt(1), rst.getInt(2), rst.getDate(3), rst.getTime(4), rst.getDate(5), rst.getTime(6)));
        }
        return userLogs;
    }


    //get userslog from database
    public ArrayList<UserLog> getUserLogByUserId(int userId, Date searchingDate) throws SQLException {
        ArrayList<UserLog> userLogs = new ArrayList<>();
        ResultSet rst = st.executeQuery("SELECT * FROM USERSLOG WHERE LOGINDATE='"+searchingDate+"' AND USERID=" + userId);
        while (rst.next()) {
            userLogs.add(new UserLog(rst.getInt(1), rst.getInt(2), rst.getDate(3), rst.getTime(4), rst.getDate(5), rst.getTime(6)));
        }
        return userLogs;
    }


    
}
