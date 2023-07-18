package org.fi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String productName = request.getParameter("productName");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");

            // Check if product name exists
            PreparedStatement checkProduct = connection.prepareStatement("SELECT * FROM products_1234 WHERE productName=?");
            checkProduct.setString(1, productName);
            ResultSet resultSet = checkProduct.executeQuery();

            if (resultSet.next()) {
                // Delete product
                PreparedStatement deleteProduct = connection.prepareStatement("DELETE FROM products_1234 WHERE productName=?");
                deleteProduct.setString(1, productName);
                deleteProduct.executeUpdate();
                deleteProduct.close();

                out.println("<font color='green'>Product Deleted</font>");
            } else {
                out.println("<font color='red'>Product Name Not Found</font>");
            }

            resultSet.close();
            checkProduct.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<font color='red'>Error Occurred</font>");
        }
    }
}
