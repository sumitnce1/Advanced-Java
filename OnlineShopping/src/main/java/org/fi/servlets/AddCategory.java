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

@WebServlet("/AddCategory")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // Limit the maximum file size to 5MB
public class AddCategory extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("categoryDescription");
        Part filePart = request.getPart("image");
        InputStream imageStream = filePart.getInputStream();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO categories_0016 (categoryId, categoryName, categoryDescription, image) VALUES (?, ?, ?, ?)");
            statement.setString(1, categoryId);
            statement.setString(2, categoryName);
            statement.setString(3, categoryDescription);
            statement.setBlob(4, imageStream);
            statement.executeUpdate();

            statement.close();
            connection.close();

            response.getWriter().println("Category added successfully!"); // Display success message
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Invalid entry. Category not added!"); // Display failure message
        }
    }
}
