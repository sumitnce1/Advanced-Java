<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Authenticate</title>
</head>
<body>
<%
    Class.forName("com.mysql.cj.jdbc.Driver");

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaee_0016", "root", "Admin@123");
         PreparedStatement psAuthenticate = connection.prepareStatement("SELECT * FROM users_0016 WHERE userName=? AND password=?");) {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        psAuthenticate.setString(1, userName);
        psAuthenticate.setString(2, password);

        try (ResultSet result = psAuthenticate.executeQuery();) {
            if (result.next()) {
                response.sendRedirect("category.jsp");
            } else {
                response.sendRedirect("login.html");
            }
        }
    }
%>
</body>
</html>
