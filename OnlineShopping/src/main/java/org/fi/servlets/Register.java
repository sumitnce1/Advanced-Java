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

/**
 * Servlet implementation class RegisterNewUser
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		

			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");
					PreparedStatement checkAlreadyUser = connection.prepareStatement("SELECT * FROM users_0016 WHERE username = ?");
					PreparedStatement insertNewUser = connection.prepareStatement("INSERT INTO users_0016 (username, password, name, email, phone, city) VALUES (?, ?, ?, ?, ?, ?)")) 
			{
				
				String username = request.getParameter("username");
				
				// Check if user already exists
				checkAlreadyUser.setString(1, username);
				ResultSet resultSet = checkAlreadyUser.executeQuery();
				
				if (resultSet.next()) {
					// User already exists then show message
					try (PrintWriter out = response.getWriter()) {
						out.println("<font color='red'>User already exists.</font>");
					}
				} else {
					// User does not exist, insert new user data
					String password = request.getParameter("password");
					String name = request.getParameter("name");
					String email = request.getParameter("email");
					String phone = request.getParameter("phone");
					String city = request.getParameter("city");
					
					insertNewUser.setString(1, username);
					insertNewUser.setString(2, password);
					insertNewUser.setString(3, name);
					insertNewUser.setString(4, email);
					insertNewUser.setString(5, phone);
					insertNewUser.setString(6, city);
					
					insertNewUser.executeUpdate();
					
					try (PrintWriter out = response.getWriter()) {
						out.println("<font color='green'>User registered successfully.</font>");
						out.println("<font color='green'><a href='login.html'>Login</a></font>");
					}
				}
			} catch (SQLException sql) {
				sql.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}