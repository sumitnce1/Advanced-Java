package org.sumitshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import org.sumitshopuser.dao.UserDAO;
import org.sumitshopuser.dao.UserDAOImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

@WebServlet(loadOnStartup = 100, urlPatterns = {"/Authenticate"})
public class Authenticate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserDAO userDAO;
	SessionFactory hibernateFactory;

	@Override
	public void init() throws ServletException {
	    super.init();
	    Configuration hibernateConfiguration = new Configuration();
	    hibernateConfiguration.configure("hibernate.cfg.xml");
	    
	    hibernateFactory = hibernateConfiguration.buildSessionFactory();
	    
	    ServletContext application = getServletContext();
	    application.setAttribute("hibernateFactory", hibernateFactory);
	    
	    Session session = hibernateFactory.openSession();
	    userDAO = new UserDAOImpl();
	    userDAO.setSession(session);
	}


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        boolean status = userDAO.authenticate(userName, password);
        if (status) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userName", userName);
            response.sendRedirect("category.jsp"); // Category URL
        } else {
        	out.println("<html><head><style>a:link, a:visited { background-color: #8A2BE2; color: white; padding: 10px 20px; text-decoration: none; }</style><body><div style='text-align: center; margin-top: 40vh;'>"
	                + "<h3 style='color: red;'>Oops! User Authentication Failed. Try Again to Login</h3>"
	                + "<h5><a href='login.html'>Click Here To Login</a></h5>"
	                + "</div></body></head></html>");
        }
    }
}