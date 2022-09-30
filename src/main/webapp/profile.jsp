<%-- 
    Document   : profile
    Created on : May 15, 2022, 3:45:02 AM
    Author     : Saqib
--%>

<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/mystyle.css">
        <title>Profile Page</title>

    </head>

    <body>
        <%
            String success = (String) session.getAttribute("success");
            User user = (User) session.getAttribute("user");
            String email = user.getEmail();
            String fullname = user.getFullName();
            String password = user.getPassword();
            String phone = user.getPhone();
        %>


        <%@ include file="includes/header.jsp" %>





        <div class="container" style="margin-top:20px; margin-bottom: 120px;">

            <form action="ProfileUpdateServlet" style="width: 70%;">
                <h3 style="color:red;text-align: center"><%= success%></h3>
                <div class="row">
                    <div class="col-25">
                        <label for="email">Email</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="email" name="email" value="<%= email%>" placeholder="Your Email" readonly>
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


                <br>
                <div class="row">


                    <a href="ProfileDeleteServlet?userid=<%=user.getUserId()%>"  > <input type="button" id="loginBtn" value="Delete Profile" ></a>
                    <input type="submit" value="Update">
                </div>
            </form>
        </div>



        <%@ include file="includes/footer.jsp" %>


    </body>

</html>