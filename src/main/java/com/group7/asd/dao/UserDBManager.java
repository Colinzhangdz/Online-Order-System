
package com.group7.asd.dao;

import com.alibaba.fastjson.JSONObject;
import com.group7.asd.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;


public class UserDBManager {

    private Statement st;

    public UserDBManager(Connection conn) throws SQLException {

        st = conn.createStatement();
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


    public void addUser1(User user) throws SQLException {
        st.executeUpdate("INSERT INTO USERS(USERID, EMAIL, PASSWORD, FULLNAME, PHONE, USERTYPE) VALUES('" + user.getUserId() + "','" + user.getEmail() + "','" + user.getPassword() + "','" + user.getFullName() + "','" + user.getPhone() + "','" + user.getUserType() + "')");
    }

//update user in database
    public void updateUser(User user) throws SQLException {
        st.executeUpdate("UPDATE USERS SET PASSWORD ='" + user.getPassword() + "',FULLNAME ='" + user.getFullName() + "',PHONE ='" + user.getPhone() + "' WHERE EMAIL ='" + user.getEmail()+"'");
    }
//delete user in database

    public void deleteUser(int id) throws SQLException {
        st.executeUpdate("DELETE FROM USERS WHERE USERID =" + id);
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

    public void saveOrder(String orderNo,Double totalMoney,String userId) throws SQLException {
        st.executeUpdate("INSERT INTO order_information(order_no, totalMoney, user_id) VALUES('" + orderNo + "','" + totalMoney + "','" + userId  + "')");
    }

    public void saveOrderDetail(JSONObject jsonObject, String orderNo) throws SQLException {
        st.executeUpdate("INSERT INTO order_detail(order_no, product_name, price) VALUES('" + orderNo + "','" + jsonObject.getString("pro_name") + "','" + jsonObject.getString("pro_price") + "')");
    }

    public User findUser1(int id) throws SQLException {
        User user = null;

        ResultSet rst = st.executeQuery("SELECT * FROM USERS WHERE USERID=" + id);
        if (rst.next()) {
        user = new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getBoolean(7));
        }

        rst.close();
        return user;
    }


    public User findUser2(int id) throws SQLException {
        //User user = null;
        User user = new User();
        //ResultSet rs = st.executeQuery("SELECT * FROM USERS WHERE USERID=" + id);
        //if (rst.next()) {
            //user = new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getBoolean(7));
        //}

        String sql = "SELECT * FROM USERS WHERE USERID=" + id;

        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            user.setUserId(Integer.parseInt(rs.getString("userid")));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setFullName(rs.getString("fullname"));
            user.setPhone(rs.getString("phone"));
            user.setUserType(rs.getString("usertype"));
            user.setIsActive(rs.getBoolean("isactive"));
        }

        rs.close();
        return user;
    }

}
