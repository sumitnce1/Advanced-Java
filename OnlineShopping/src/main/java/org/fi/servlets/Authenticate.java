package org.fi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try (PrintWriter out = response.getWriter()) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");
					PreparedStatement suAuthenticateUser = connection.prepareStatement("select * from users_0016 where userName=? and password=?")) 
			{
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				
				suAuthenticateUser.setString(1, userName);
				suAuthenticateUser.setString(2, password);
				
				try (ResultSet result = suAuthenticateUser.executeQuery()) {
					if(result.next())
						response.sendRedirect(request.getContextPath() + "/welcome.html");
						//response.sendRedirect("Category"); // Category URL
						//out.println("<font color='green'> User Authenticated</font>");
					else
						out.println("<font color='red'> User Authenticated Failed</font>");
				}
					
			} catch (SQLException sql) {	
				sql.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}