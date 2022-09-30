/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group7.asd.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Saqib
 */
public class UserLog implements Serializable{

    private int userLogId;
    private int userId;
    private Date loginDate;
    private Time loginTime;
    private Date logoutDate;
    private Time logoutTime;

    public UserLog() {
    }

    public UserLog(int userLogId, int userId, Date loginDate, Time loginTime, Date logoutDate, Time logoutTime) {
        this.userLogId = userLogId;
        this.userId = userId;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
        this.logoutDate = logoutDate;
        this.logoutTime = logoutTime;
    }

    public int getUserLogId() {
        return userLogId;
    }

    public void setUserLogId(int userLogId) {
        this.userLogId = userLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Time getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Time loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    public Time getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Time logoutTime) {
        this.logoutTime = logoutTime;
    }

}
