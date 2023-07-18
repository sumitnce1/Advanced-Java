<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>JSP First App</title>
</head>
<body>
	<h1><%= new Date() %></h1>
	<h1>Current Time is</h1>
	<%
	for (int iTmp = 0; iTmp < 5; iTmp++) {
	%>
		<h1><%= new Date() %></h1>
	<%
	}
	%>
</body>
</html>
