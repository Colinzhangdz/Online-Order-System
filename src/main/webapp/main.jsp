<%@ page import="com.group7.asd.model.User" %><%--
  Created by IntelliJ IDEA.
  User: pan11
  Date: 3/10/2022
  Time: 8:00 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    User user = (User) session.getAttribute("user");

    String type = user.getUserType();
%>

<a href="profile.jsp" > <input type="button" value="Profile page" ></a>
<a href="staff.html" > <input type="button" value="Staff Management" ></a>
<a href="customer.html" > <input type="button" value="Customer Management" ></a>
<a href="check.html" > <input type="button" value="Checklist" ></a>

<% if (type.equals("STAFF")) { %>
<jsp:include page="staffMain.jsp" flush="true" />
<% } else { %>
<jsp:include page="customerMain.jsp" flush="true" />
<% } %>



</body>
</html>
