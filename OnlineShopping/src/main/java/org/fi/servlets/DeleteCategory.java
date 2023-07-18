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

@WebServlet("/DeleteCategory")
public class DeleteCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryName = request.getParameter("categoryName");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");

            // Check if category name exists
            PreparedStatement checkCategory = connection.prepareStatement("SELECT * FROM category_1234 WHERE categoryName=?");
            checkCategory.setString(1, categoryName);
            ResultSet resultSet = checkCategory.executeQuery();

            if (resultSet.next()) {
                // Delete category
                PreparedStatement deleteCategory = connection.prepareStatement("DELETE FROM category_1234 WHERE categoryName=?");
                deleteCategory.setString(1, categoryName);
                deleteCategory.executeUpdate();
                deleteCategory.close();

                out.println("<font color='green'>Category Deleted</font>");
            } else {
                out.println("<font color='red'>Category Name Not Found</font>");
            }

            resultSet.close();
            checkCategory.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<font color='red'>Error Occurred</font>");
        }
    }
}
