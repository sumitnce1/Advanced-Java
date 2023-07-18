<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.sumit.pojo.Category"%>
<%@page import="org.sumitcategory.dao.CategoryDAOImpl"%>
<%@page import="org.sumitcategory.dao.CategoryDAO"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category</title>
</head>
<body>
    <%
        Connection connection = (Connection) application.getAttribute("globalConnection");
        CategoryDAO categoryDAO = new CategoryDAOImpl(connection);
    %>
    Welcome User <b><%= session.getAttribute("userName") %></b>.
    <br>
    <table border='1'>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Image</th>
        </tr>
        <%
            ArrayList<Category> list = categoryDAO.allCategories();
            Iterator<Category> iter = list.iterator();
            while (iter.hasNext()) {
                Category objCategory = iter.next();
        %>
            <tr>
                <td><a href='products.jsp?categoryId=<%= objCategory.getCategoryId() %>'><%= objCategory.getCategoryName() %></a></td>
                <td><%= objCategory.getCategoryDescription() %></td>
                <td><img src='Images/<%= objCategory.getCategoryImageUrl() %>' height='60px' width='60px' /></td>
            </tr>
        <%
            }
        %>
    </table>
</body>
</html>
