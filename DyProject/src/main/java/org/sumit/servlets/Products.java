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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Products")
public class Products extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Connection connection;
    PreparedStatement psProductsByCategoryId;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init of Products");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");
            psProductsByCategoryId = connection.prepareStatement("SELECT * FROM products_1234 WHERE categoryId=?");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize database");
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy of Products");
        try {
            if (psProductsByCategoryId != null)
                psProductsByCategoryId.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        System.out.println("doGet of - Products");

        try {
            String tmpCategoryId = request.getParameter("categoryId");
            int categoryId = Integer.parseInt(tmpCategoryId);
            psProductsByCategoryId.setInt(1, categoryId);

            try (ResultSet result = psProductsByCategoryId.executeQuery()) {
                out.println("<html>");
                out.println("<body>");
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Name</th>");
                out.println("<th>Description</th>");
                out.println("<th>Price</th>");
                out.println("<th>Image</th>");
                out.println("</tr>");

                while (result.next()) {
                    out.println("<tr>");
                    out.println("<td>" + result.getString("productName") + "</td>");
                    out.println("<td>" + result.getString("productDescription") + "</td>");
                    out.println("<td>" + result.getString("productPrice") + "</td>");
                    out.println("<td><img src='Images/" + result.getString("productImageUrl") + "' height='60px' width='60px' /></td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
