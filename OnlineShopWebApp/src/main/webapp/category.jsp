<%@ page import="org.sumitshop.hibernate.entity.Category" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="javax.persistence.Query" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Category</title>
</head>
<body>
 	<h2> Welcome To Online Shop - <b><%= session.getAttribute("userName")%></b>&nbsp; &nbsp; &nbsp; &nbsp; <a href='Logout'>Logout</a> &nbsp; &nbsp; &nbsp; <a href='ListCart'> Cart&#128722;</a><br/><br/> </h2>
    <br>
    <table border='1'>
        <%
        SessionFactory hibernateFactory = (SessionFactory) application.getAttribute("hibernateFactory");
        try (Session hibernateSession = hibernateFactory.openSession()) {
            Query categories = hibernateSession.createQuery("from Category");
            Iterator<Category> iter = categories.getResultList().iterator();
            while(iter.hasNext()) {
                Category objCategory = iter.next();
        %>
        <tr>
            <td><a href='products.jsp?categoryId=<%= objCategory.getCategoryId() %>'><%= objCategory.getCategoryName() %></a></td>
            <td><%= objCategory.getCategoryDescription() %></td>
            <td><img src='Images/<%= objCategory.getCategoryImageUrl() %>' height='60px' width='60px' /></td>           
        </tr>
        <%
            }
        }
        %>
    </table>
</body>
</html>
