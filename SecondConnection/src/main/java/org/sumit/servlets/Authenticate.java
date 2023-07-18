package org.sumit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Authenticate
 */

public class Authenticate extends HttpServlet {
	public static final long serialVersionUID = 1L;

	Connection connection;
	PreparedStatement authenticateUser;
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{		
			super.init(config);
			System.out.println("init of - Authenticate");
			
			try {
				String driverClass = config.getInitParameter("driverClass");
				String dbUrl = config.getInitParameter("dbUrl");
				String dbUser = config.getInitParameter("dbUserName");
				String dbPwd = config.getInitParameter("dbPassword");
				
				Class.forName(driverClass);
				connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
				authenticateUser = connection.prepareStatement("select * from users_0016 where userName=? and password=?");
				}
			
			catch(ClassNotFoundException | SQLException e){
				e.printStackTrace();
				throw new ServletException("Failed to initializ database");
			}
	}
	@Override
	public void destroy() {
		System.out.println("destory of - Authenticate");
		
		try {
			if(authenticateUser!=null)
				authenticateUser.close();
			if(connection!=null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("doPost of - Authenticate");
		PrintWriter out = response.getWriter();
			
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			
			try {
			authenticateUser.setString(1, userName);
			authenticateUser.setString(2, password);
			
			try (ResultSet result = authenticateUser.executeQuery()) {
				if(result.next())
					response.sendRedirect("Category"); // Category URL
				else
					out.println("Authenication failed");
			} catch(SQLException sql)
			{
				sql.printStackTrace();
			}
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
}