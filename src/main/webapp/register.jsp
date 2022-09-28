<%--
  Created by IntelliJ IDEA.
  User: pan11
  Date: 28/9/2022
  Time: 11:05 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Register Page</title>

</head>

<body>
<%
  String error = (String) session.getAttribute("error");

  String email = request.getParameter("email") == null ? "" : request.getParameter("email");
  String fullname = request.getParameter("fullname") == null ? "" : request.getParameter("fullname");
  String password = request.getParameter("password") == null ? "" : request.getParameter("password");
  String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone");


%>


<div class="iotbay-heading">
  <h4>IOT BAY</h4>
</div>
<div class="container ">
  <header class="header">

    <h1>Registration</h1>


  </header>

</div>




<div class="container" style="margin-top:20px; margin-bottom: 120px;">

  <form action="RegisterServlet" style="width: 70%;">
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
    <div class="row">
      <div class="col-25">
        <label for="fullname">Full Name</label>
      </div>
      <div class="col-75">
        <input type="text" id="fullname" name="fullname" value="<%=fullname%>" placeholder="Your Full Name..">
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="lname">Phone</label>
      </div>
      <div class="col-75">
        <input type="text" id="lname" name="phone" value="<%= phone%>" placeholder="Your Phone Number">
      </div>
    </div>

    <div class="row">
      <div class="col-25">
        <label for="lname"> User Type</label>
      </div>
      <div class="col-75">
        <select name="usertype" id="usertype">
          <option value="CUSTOMER">Customer</option>
          <option value="STAFF">Staff</option>

        </select>
      </div>
    </div>


    <br>
    <div class="row">


      <a href="LoginRedirectServlet"  > <input type="button" id="loginBtn" value="Already Have Account" ></a>
      <input type="submit" value="Register">
    </div>
  </form>
</div>






</body>
</html>
