<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Product-Shop</title>
</head>
<body>
    <table border='1'>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Image</th>
            <th>Add to Cart</th>
        </tr>
        <%
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaee_0016", "root", "Admin@123");
            PreparedStatement psProductsByCategoryId = connection.prepareStatement("select * From products_1234 where categoryId=?");
            
            String tmpCategoryId = request.getParameter("categoryId");
            int categoryId = Integer.parseInt(tmpCategoryId);
            psProductsByCategoryId.setInt(1, categoryId);
            
            ResultSet result = psProductsByCategoryId.executeQuery();
            
            while (result.next()) {
        %>
            <tr>
                <td><%= result.getString("ProductName") %></td>
                <td><%= result.getString("ProductDescription") %></td>
                <td><%= result.getString("ProductPrice") %></td>
                <td><img src='Images/<%= result.getString("productImageUrl") %>' height='60px' width='60px'/></td>
                <td><a href='addcart.jsp?productId=<%= result.getInt("ProductId") %>'>Add to Cart</a></td>
            </tr>
        <%
            }
            
            result.close();
            psProductsByCategoryId.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        %>
    </table>
</body>
</html>
