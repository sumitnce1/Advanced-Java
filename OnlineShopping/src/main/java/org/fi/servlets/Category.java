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

@WebServlet("/Category")
public class Category extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");
					PreparedStatement psAllCategories = connection.prepareStatement("select * From category_1234");
					ResultSet result = psAllCategories.executeQuery()) 
			{
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}		
	}
}
