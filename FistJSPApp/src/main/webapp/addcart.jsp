<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add to Cart</title>
</head>
<body>
    <%
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaee_0016", "root", "Admin@123");
        PreparedStatement psProductDetails = connection.prepareStatement("SELECT productPrice, productName, productImageUrl FROM products_1234 WHERE categoryId=? AND productId=?");

        String tmpCategoryId = request.getParameter("categoryId");
        String tmpProductId = request.getParameter("productId");

        if (tmpCategoryId != null && tmpProductId != null) {
            int categoryId = Integer.parseInt(tmpCategoryId);
            int productId = Integer.parseInt(tmpProductId);

            psProductDetails.setInt(1, categoryId);
            psProductDetails.setInt(2, productId);

            try (ResultSet result = psProductDetails.executeQuery()) {
                if (result.next()) {
                    float productPrice = result.getFloat("productPrice");
                    String productName = result.getString("productName");
                    String productImageUrl = result.getString("productImageUrl");
                    CartItem item = new CartItem(categoryId, productId, productName, productPrice, productImageUrl);

                    ArrayList<CartItem> cart = (ArrayList<CartItem>) request.getAttribute("cart");

                    if (cart == null) {
                        cart = new ArrayList<>();
                        request.setAttribute("cart", cart);
                    }

                    cart.add(item);
                }
            }
        }

        response.sendRedirect("ListCart");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    %>
</body>
</html>
