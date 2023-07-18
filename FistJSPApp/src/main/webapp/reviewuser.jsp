<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Review User</title>
</head>
<body>
    <jsp:useBean id="objUser" class="org.sumit.pojo.User" scope="session"></jsp:useBean>
    <jsp:setProperty property="*" name="objUser" />

    Review User Registration Detail:<br/><br/>
    Name: <jsp:getProperty property="name" name="objUser"/><br/><br/>
    Password: **************<br/><br/>
    User Name: <jsp:getProperty property="userName" name="objUser"/><br/><br/>
    Email: <jsp:getProperty property="email" name="objUser"/><br/><br/>
    Phone: <jsp:getProperty property="phone" name="objUser"/><br/><br/>
    City: <jsp:getProperty property="city" name="objUser"/><br/><br/>

    <a href="confirm.jsp">Confirm</a>&nbsp; &nbsp; &nbsp;
    <a href="newuser.html">Cancel</a>
</body>
</html>
