package org.fi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Servlet implementation class HeaderServlet
 */
@WebServlet("/GetHeaders")
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<table border ='1'>");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Value</th>");
		out.println("</tr>");
		
		Enumeration<String> headerNames = request.getHeaderNames();
		
		while (headerNames.hasMoreElements())
		{
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			
			out.println("<tr>");
			out.println("<td>" + headerName +"</td>");
			out.println("<td>" + headerValue+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}