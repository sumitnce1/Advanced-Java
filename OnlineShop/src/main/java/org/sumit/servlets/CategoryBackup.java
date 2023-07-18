package org.sumit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.sumitcategory.dao.CategoryDAO;
import org.sumitcategory.dao.CategoryDAOImpl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Category")
public class CategoryBackup extends HttpServlet {
    Connection connection;
    CategoryDAO categoryDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init of- Category");
        ServletContext application = getServletContext();
        connection = (Connection) application.getAttribute("globalConnection");
        categoryDAO = new CategoryDAOImpl(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.htm");
            return;
        }

        out.println("<html>");
        out.println("<body>");
        out.println("<h2> Welcome To Online Shop - <b>" + session.getAttribute("userName") + "</b>&nbsp; &nbsp; &nbsp; &nbsp; <a href='Logout'>Logout</a> &nbsp; &nbsp; &nbsp; <a href='ListCart'> Cart&#128722;</a><br/><br/> </h2>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>Name</th>");
        out.println("<th>Description</th>");
        out.println("<th>Image</th>");
        out.println("</tr>");

        ArrayList<org.sumit.pojo.Category> list = categoryDAO.allCategories();
        for (org.sumit.pojo.Category objCategory : list) {
            out.println("<tr>");
            out.println("<td><a href='Products?categoryId=" + objCategory.getCategoryId() + "'>" + objCategory.getCategoryName() + "</a></td>");
            out.println("<td>" + objCategory.getCategoryDescription() + "</td>");
            out.println("<td><img src='Images/" + objCategory.getCategoryImageUrl() + "' height='60px' width='60px' /></td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
