
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@ page import="com.group7.asd.dao.UserLogDBManager" %>
<%@ page import="com.group7.asd.model.User" %>
<%@ page import="com.group7.asd.model.UserLog" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/mystyle.css">
        <title>Users Log Page</title>

    </head>

    <body>
        <%
            Date searchDate = null;
            if (request.getParameter("date") != null) {
                searchDate = Date.valueOf(request.getParameter("date"));
            }

            UserLogDBManager manager = (UserLogDBManager) session.getAttribute("userLogDBManager");
            User user = (User) session.getAttribute("user");

            ArrayList<UserLog> usersLog;
            usersLog = null;
            if (searchDate == null) {
                usersLog = manager.getUserLogByUserId(user.getUserId());
            } else {
                usersLog = manager.getUserLogByUserId(user.getUserId(), searchDate);
            }

        %>








        <div class="container" style=" margin-top:50px;margin-bottom:50px">
            <form action="SearchUserLogServlet" style="width: 70%;">
                <div class="row">
                    <div class="col-25">
                        <label for="email">Select Date</label>
                    </div>
                    <div class="col-75">
                        <input type="date" id="email" name="date"  value="">
                    </div>
                </div>
                <br>
                <div class="row">
                    <input type="submit" value="Search">
                </div>
            </form>
        </div>
        <div class="container" style=" margin-top:50px;margin-bottom:50px">

            <table id="customers" style="margin-top: 30px;">
                <tr>
                    <th>ID </th>
                    <th>UserId</th>
                    <th>Login Date</th>
                    <th>Login Time</th>
                    <th>Logout Date</th>
                    <th>Logout Time</th>
                </tr>
                <%    for (UserLog _userlog : usersLog) {
                %>
                <tr>
                    <td><%= _userlog.getUserLogId()%> </td>
                    <td><%= _userlog.getUserId()%> </td>
                    <td><%= _userlog.getLoginDate()%> </td>
                    <td><%= _userlog.getLoginTime()%> </td>
                    <td><%= _userlog.getLogoutDate() == null ? "-" : _userlog.getLogoutDate()%> </td>
                    <td><%= _userlog.getLogoutTime() == null ? "-" : _userlog.getLogoutTime()%> </td>
                </tr>
                <%
                    }%>

            </table>
        </div>

        <br>
        <br>
        <br>



    </body>

</html>