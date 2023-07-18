<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Category-Shop</title>
</head>
<body>
    <table border='1'>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Image</th>
        </tr>
        <%
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaee_0016", "root", "Admin@123");
            PreparedStatement psAllCategories = connection.prepareStatement("select * from category_1234");
            ResultSet result = psAllCategories.executeQuery();) {

            while (result.next()) {
        %>
            <tr>
                <td><a href='product.jsp?categoryId=<%= result.getInt(1) %>'><%= result.getString(2) %></a></td>
                <td><%= result.getString(3) %></td>
                <td><img src='Images/<%= result.getString(4) %>' height='60px' width='60px'/></td>
            </tr>
        <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        %>
    </table>
</body>
</html>
