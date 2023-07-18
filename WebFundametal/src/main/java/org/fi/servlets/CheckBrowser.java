package org.fi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CheckBrowser
 */
@WebServlet("/CheckBrowser")
public class CheckBrowser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String userAgent = request.getHeader("user-agent");
		
		if(userAgent.indexOf("Firfox")!=-1)
		{
			out.println("<b>You are using Mozila</b>");
		}
		else if(userAgent.indexOf("Edg")!=-1)
		{
			out.println("<b>You are using Microsoft</b>");
		}
		else if(userAgent.indexOf("Chrome")!=-1)
		{
			out.println("<b>You are using Chrome Browser</b>");
		}
		else 
			out.println("<b>You are using Unusal Browser</b>");
	}

}
