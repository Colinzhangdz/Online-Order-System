
package com.group7.asd.dao;

import com.group7.asd.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import uts.isd.model.Device;

/**
 *
 * @author Huiming
 */
public class UserDBManager {

    private Statement st;

    public UserDBManager(Connection conn) throws SQLException {

        st = conn.createStatement();
    }

    public UserDBManager() {

    }

    //validate user from database
    public int validateUser(String username, String password) throws SQLException {
        int isExist = 0;
        ResultSet rst = st.executeQuery("SELECT * FROM USERS WHERE EMAIL='" + username + "' AND PASSWORD='" + password + "'");
        if (rst.next()) {
            if (!rst.getBoolean("ISACTIVE")) {
                isExist = 2;
            } else {
                isExist = 1;
            }
        }
        rst.close();
        return isExist;
    }

    //validate email exist from database
    public int checkEmailExist(String username) throws SQLException {
        int isExist = 0;
        ResultSet rst = st.executeQuery("SELECT * FROM USERS WHERE EMAIL='" + username + "'");
        if (rst.next()) {
            if (!rst.getBoolean("ISACTIVE")) {
                isExist = 2;
            } else {
                isExist = 1;
            }
        }
        rst.close();
        return isExist;
    }

//create user in database
    public void addUser(User user) throws SQLException {
        st.executeUpdate("INSERT INTO USERS(EMAIL, PASSWORD, FULLNAME, PHONE, USERTYPE) VALUES('" + user.getEmail() + "','" + user.getPassword() + "','" + user.getFullName() + "','" + user.getPhone() + "','" + user.getUserType() + "')");
    }

//update user in database
    public void updateUser(User user) throws SQLException {
        st.executeUpdate("UPDATE USERS SET PASSWORD ='" + user.getPassword() + "',FULLNAME ='" + user.getFullName() + "',PHONE ='" + user.getPhone() + "' WHERE EMAIL ='" + user.getEmail()+"'");
    }
//delete user in database

    public void deleteUser(int id) throws SQLException {
        st.executeUpdate("UPDATE USERS SET ISACTIVE=FALSE WHERE USERID =" + id);
    }

    public User findUser(String username, String password) throws SQLException {
        User user = null;
        ResultSet rst = st.executeQuery("SELECT * FROM USERS WHERE EMAIL='" + username + "' AND PASSWORD='" + password + "'");
        if (rst.next()) {
            user = new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getBoolean(7));
        }
        rst.close();
        return user;
    }

}
