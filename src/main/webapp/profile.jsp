<%@ page import="com.group7.asd.model.User" %>


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

            String type = user.getUserType();
        %>

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
                        <input type="password" id="password" name="password" value="<%=password%>" placeholder="Passowrd">
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
                <!-- check user's condition, if user is staff then bring staff page, if customer, bring user page -->
                <div class="row">


                    <a href="ProfileDeleteServlet?userid=<%=user.getUserId()%>" > <input type="button" id="loginBtn" value="Delete Profile" ></a>
                    <input type="submit" value="Update">
                    <a href="login.jsp" > <input type="button" value="Logout" ></a>
                    <% if (type.equals("STAFF")) { %>
                    <jsp:include page="staffMain.jsp" flush="true" />
                    <% } else { %>
                    <jsp:include page="customerMain.jsp" flush="true" />
                    <% } %>


                </div>
            </form>
        </div>






    </body>

</html>