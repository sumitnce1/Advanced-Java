package org.fi.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/AddProduct")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // Limit the maximum file size to 5MB
public class AddProduct extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String productId = request.getParameter("productId");
        String categoryId = request.getParameter("categoryId");
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productPrice = request.getParameter("productPrice");        
        Part filePart = request.getPart("image");
        InputStream imageStream = filePart.getInputStream();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO products_0016 (productId, categoryId, productName, productDescription, productPrice, image) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, productId);
            statement.setString(2, categoryId);
            statement.setString(3, productName);
            statement.setString(4, productDescription);
            statement.setString(5, productPrice);
            statement.setBlob(6, imageStream);
            statement.executeUpdate();

            statement.close();
            connection.close();

            response.getWriter().println("Product added successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Invalid entry. Product not added!");
        }
    }
}
