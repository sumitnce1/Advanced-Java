<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
    a:link, a:visited { background-color: #8A2BE2; color: white; padding: 10px 20px; text-decoration: none; }    
    </style>
    <title>Confirm User</title>
</head>
<body>
<div style='text-align: center; margin-top: 40vh;'>
    <jsp:useBean id="objUser" scope="session" type="org.sumit.pojo.User" />
    <% 
        boolean registrationSuccessful = true;

        // Check if any input field is null
        if (objUser.getUserName() == null || objUser.getPassword() == null ||
            objUser.getName() == null || objUser.getEmail() == null ||
            objUser.getPhone() == null || objUser.getCity() == null) {
            registrationSuccessful = false;
        } else {
            try {
                objUser.save();
            } catch (Exception e) {
                registrationSuccessful = false;
                e.printStackTrace();
            }
        }
        
        if (registrationSuccessful) {
    %>
            <h2 class="text-success">You have been registered successfully!</h2><br>
            <p><a href='login.html'>Click Here To Login</a></p>
    <% 
        } else {
    %>
            <h2 class="text-danger">Sorry, you have not been registered. Please fill in all the registration fields and try again.</h2><br>
            <p><a href='newuser.html'>Click Here To Register</a></p>
    <% 
        }
    %>
    </div>
</body>
</html>
