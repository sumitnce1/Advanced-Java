<%@page import="org.sumit.mvchiberbootapp.entity.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>showCategory</title>
</head>
<body>
    <h1>Welcome <b><%= session.getAttribute("userName") %></b></h1><br/>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Image</th>
        </tr>
        <% List<Category> list = (List<Category>)request.getAttribute("data");
        for (Category objCategory : list) 
        { %>
            <tr>
                <td><a href="product?categoryId=<%= objCategory.getCategoryId() %>"><%=objCategory.getCategoryName() %></a></td>
                <td><%=objCategory.getCategoryDescription() %></td>
                <td><%=objCategory.getCategoryImageUrl() %></td>
            </tr>
          <% 
         } 
        
        %>
    </table>
</body>
</html>
