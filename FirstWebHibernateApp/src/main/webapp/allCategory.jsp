<%@ page import="org.sumitweb.hibernate.entity.Category" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="javax.persistence.Query" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>AllCategory</title>
</head>
<body>
    <table border='1'>
        <%
        SessionFactory hibernateFactory = (SessionFactory) application.getAttribute("hibernateFactory");
        try (Session hibernateSession = hibernateFactory.openSession()) {
            Query allCategories = hibernateSession.createQuery("from Category");
            Iterator<Category> iter = allCategories.getResultList().iterator();
            while(iter.hasNext()) {
                Category objCategory = iter.next();
        %>
        <tr>
            <td>
                <a href='products?categoryId=<%= objCategory.getCategoryId() %>'>
                    <%= objCategory.getCategoryName() %>
                </a>
            </td>
            <td><%= objCategory.getCategoryDescription() %></td>
            <td><%= objCategory.getCategoryImageUrl() %></td>
        </tr>
        <%
            }
        }
        %>
    </table>
</body>
</html>
