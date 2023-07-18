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

@WebServlet("/Products")
public class Products extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaee_0016", "root", "Admin@123");				
					PreparedStatement psProductsByCategoryId = connection.prepareStatement("select * From products_1234 where categoryId=?");
					)
			{
				String tmpCategoryId = request.getParameter("categoryId");
				int categoryId = Integer.parseInt(tmpCategoryId);

				psProductsByCategoryId.setInt(1, categoryId);

				try (ResultSet result = psProductsByCategoryId.executeQuery())
				{

					out.println("<html>");
					out.println("<body>");

					out.println("<table border='1'>");

					out.println("<tr>");
					out.println("<th>Name</th>");
					out.println("<th>Description</th>");
					out.println("<th>Price</th>");
					out.println("<th>Image</th>");
					out.println("</tr>");

					while(result.next()) 
					{
						out.println("<tr>");
						out.println("<td>" + result.getString("ProductName") + "</td>");
						out.println("<td>" + result.getString("ProductDescription") + "</td>");
						out.println("<td>" + result.getString("ProductPrice") + "</td>");
						out.println("<td><img src= 'Images/" + result.getString("productImageUrl") +"' heigth='60px' width='60px'/></td>");						
						out.println("</tr>");
					}
					
				}

				out.println("</table>");
				out.println("</body>");
				out.println("</html>");				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} catch (ClassNotFoundException e){
			e.printStackTrace();
		}		
	}
}
