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

@WebServlet("/Category")
public class Category extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Connection connection;
    PreparedStatement psAllCategories;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init of Category");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");
            psAllCategories = connection.prepareStatement("SELECT * FROM category_1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize database");
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy of Category");
        try {
            if (psAllCategories != null)
                psAllCategories.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet of-Category");
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

			while(result.next()) 
			{
				out.println("<tr>");
				out.println("<td><a href='Products?categoryId=" + result.getInt("categoryId") + "'>" + result.getString("categoryName") + "</a></td>");					
				out.println("<td>" + result.getString("categoryDescription") + "</td>");
				out.println("<td><img src= 'Images/" + result.getString("categoryImageUrl") +"' heigth='60px' width='60px'/></td>");						
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