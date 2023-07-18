package org.sumitweb.servlets;

import java.io.IOException;

import org.sumitweb.hibernate.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup = 100, urlPatterns = {"/AddCategory"})
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SessionFactory hibernateFactory;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Configuration hibernateConfiguration = new Configuration();
		hibernateConfiguration.configure("hibernate.cfg.xml");
		hibernateFactory = hibernateConfiguration.buildSessionFactory();
		ServletContext application = getServletContext();
		application.setAttribute("hibernateFactory", hibernateFactory);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Session hibernateSession = hibernateFactory.openSession()) 
		{
			String categoryName = request.getParameter("categoryName");
			String categoryDescription = request.getParameter("categoryDescription");
			String categoryImageUrl = request.getParameter("categoryImage");

			Category objCategory = new Category(categoryName, categoryDescription, categoryImageUrl);
			hibernateSession.beginTransaction();
			hibernateSession.save(objCategory);
			hibernateSession.getTransaction().commit();

			response.getWriter().println("Record stored in Database. :)");
		}
	}
}

