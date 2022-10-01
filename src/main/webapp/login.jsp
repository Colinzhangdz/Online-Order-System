
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/mystyle.css">
    <title>Login Page</title>
7
</head>

<body>

<%
    String error = (String) session.getAttribute("error");

    String email = request.getParameter("email") == null ? "" : request.getParameter("email");
    String password = request.getParameter("password") == null ? "" : request.getParameter("password");


%>


<div class="iotbay-heading">
    <h4>Online Order</h4>
</div>
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

