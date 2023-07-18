package org.sumit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Category extends HttpServlet {
    public Connection connection;
    public PreparedStatement psAllCategories;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init of - Category");
        try {
            String driverClass = config.getInitParameter("driverClass");
            String dbUrl = config.getInitParameter("dbUrl");
            String dbUser = config.getInitParameter("dbUserName");
            String dbPwd = config.getInitParameter("dbPassword");
            
            Class.forName(driverClass); // oracle.jdbc.oracleDriver
            
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            psAllCategories = connection.prepareStatement("SELECT * FROM category_1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize database");
            // Email support
        }
    }

    @Override
    public void destroy() {
        try {
            if (psAllCategories != null) {
                psAllCategories.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("destroy Category");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet of Category");
        PrintWriter out = response.getWriter();
        try (ResultSet result = psAllCategories.executeQuery()) {
            out.println("<html>");
            out.println("<body>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>Description</th>");
            out.println("<th>Image</th>");
            out.println("</tr>");
            while (result.next()) {
                out.println("<tr>");
                out.println("<td><a href='Products?categoryId=" + result.getInt("categoryId") + "'>"
                        + result.getString("categoryName") + "</a></td>");
                out.println("<td>" + result.getString("categoryDescription") + "</td>");
                out.println("<td><img src='Images/" + result.getString("categoryImageUrl") + "' height='60px' width='60px' /></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
