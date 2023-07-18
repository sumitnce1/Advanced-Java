package org.sumit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebServlet("/Products")
public class ProductsBackup extends HttpServlet {

	Connection connection;
	PreparedStatement psProductsByCategoryId;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("init of - Products");
		try {
			ServletContext application = getServletContext();
			connection = (Connection) application.getAttribute("globalConnection");
			psProductsByCategoryId = connection.prepareStatement("SELECT * FROM products_1234 WHERE categoryId=?");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Failed To Initialize Database"); // Email Support
		}
	}

	@Override
	public void destroy() {
		System.out.println("destroy of - Products");
		try {
			if (psProductsByCategoryId != null)
				psProductsByCategoryId.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession(false);
	    if (session == null) {
	        response.sendRedirect("login.html");
	        return;
	    }
	    try {
	        String tmpCategoryId = request.getParameter("categoryId");
	        int categoryId = Integer.parseInt(tmpCategoryId);

	        try {
	            psProductsByCategoryId.setInt(1, categoryId);
	            try (ResultSet result = psProductsByCategoryId.executeQuery()) {
	                out.println("<html>");
	                out.println("<body>");
	                out.println("<h2> Welcome To Online Shop - <b>" + session.getAttribute("userName") + "</b>&nbsp; &nbsp; &nbsp; &nbsp; <a href='Logout'>Logout</a> &nbsp; &nbsp; &nbsp; <a href='ListCart'> Cart&#128722;</a><br/><br/> </h2>");
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
	                    out.println("<td><a href= 'AddCart?categoryId=" 
	                    		+ result.getInt("categoryId") 
	                    		+ "&productId=" + result.getInt("productId") 
	                    		+ "'>Add To Cart </a></td>");
	                    out.println("</tr>");
	                }
	                out.println("</table>");
	                out.println("</body>");
	                out.println("</html>");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	    }
	}
}