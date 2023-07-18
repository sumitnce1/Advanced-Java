<%@page import="java.util.Iterator"%>
<%@page import="javax.persistence.Query"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="org.sumitshop.hibernate.entity.Product" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.cfg.Configuration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Page</title>
</head>
<body>
<h2> Welcome To Online Shop - <b><%= session.getAttribute("userName")%></b>&nbsp; &nbsp; &nbsp; &nbsp; <a href='Logout'>Logout</a> &nbsp; &nbsp; &nbsp; <a href='ListCart'> Cart&#128722;</a><br/><br/> </h2>    
    <br>
	<table border='1'>
	<tr>
		<th>Name</th>
		<th>Description</th>
		<th>Price</th>
		<th>Image</th>
		<th>Add to Cart</th>
	</tr>
	<%
	SessionFactory hibernateFactory = (SessionFactory)application.getAttribute("hibernateFactory");

	try(Session hibernateSession = hibernateFactory.openSession();) {
	    int categoryId = Integer.parseInt(request.getParameter("categoryId"));

	    Query productsByCategory = hibernateSession.createQuery("from Product p where p.prodId.categoryId = :categoryId");
	    productsByCategory.setParameter("categoryId", categoryId);

	    Iterator<Product> iter = productsByCategory.getResultList().iterator();
	    while(iter.hasNext()) {
	        Product objProduct = iter.next();
	%>
	        <tr>
	            <td><%= objProduct.getProductName()%></td>
	            <td><%= objProduct.getProductDescription()%></td>
	            <td><%= objProduct.getProductPrice()%></td>
	            <td><img src='Images/<%= objProduct.getProductImageUrl()%>'height='60px'  width='60px'/></td>
	            <td><a href='AddCart?categoryId=<%= categoryId %>&prodId=<%= objProduct.getProdId().getProductId()%>'> Add Cart </a></td>
	        </tr>
	<%
	    }
	}
	%>

</table>

</body>
</html>
