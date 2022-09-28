<%--
  Created by IntelliJ IDEA.
  User: pan11
  Date: 26/9/2022
  Time: 6:21 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Login Page</title>

</head>
<body>
<%
    String error = (String) session.getAttribute("error");
    String email = request.getParameter("email") == null ? "" : request.getParameter("email");
    String password = request.getParameter("password") == null ? "" : request.getParameter("password");
%>

<div class="container ">
    <header class="header">

        <h1>Login</h1>


    </header>

</div>

<div class="container" style="margin-top:20px; margin-bottom: 120px;">

    <form action="LoginServlet" style="width: 70%;">
        <h3 style="color:red;text-align: center"><%= error%></h3>
        <div class="row">
            <div class="col-25">
                <label for="email">Email</label>
            </div>
            <div class="col-75">
                <input type="text" id="email" name="email" value="<%= email%>" placeholder="Your Email">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="password">Password</label>
            </div>
            <div class="col-75">
                <input type="text" id="password" name="password" value="<%=password%>" placeholder="Passowrd">
            </div>
        </div>




        <br>
        <div class="row">


            <a href="RegisterRedirectServlet"  > <input type="button" id="loginBtn" value="Register New Account" ></a>
            <input type="submit" value="Login">
        </div>
    </form>
</div>

</body>
</html>
