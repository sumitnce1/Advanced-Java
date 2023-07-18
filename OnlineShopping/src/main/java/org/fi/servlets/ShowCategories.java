package org.fi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ShowCategories")
public class ShowCategories extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories_0016");
            ResultSet resultSet = statement.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Show Categories</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table border='1' style='border-collapse: collapse; border: 1px solid brown;text-align: center;'>");

            out.println("<tr>");
            out.println("<th>Category ID</th>");
            out.println("<th>Category Name</th>");
            out.println("<th>Category Description</th>");
            out.println("<th>Image</th>");
            out.println("</tr>");

            while (resultSet.next()) {
                int categoryId = resultSet.getInt("categoryID");
                String categoryName = resultSet.getString("categoryName");
                String categoryDescription = resultSet.getString("categoryDescription");
                byte[] imageData = resultSet.getBytes("image");
                String imageBase64 = Base64.getEncoder().encodeToString(imageData);

                out.println("<tr>");
                out.println("<td>" + categoryId + "</td>");
                out.println("<td>" + categoryName + "</td>");
                out.println("<td>" + categoryDescription + "</td>");
                out.println("<td><img src='data:image/jpeg;base64," + imageBase64 + "' alt='Category Image' width='100' height='100'></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("An error occurred while retrieving categories.");
        } finally {
            out.close();
        }
    }
}
