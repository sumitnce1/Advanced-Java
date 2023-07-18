package org.sumit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Connection connection;
    PreparedStatement psAuthenticateUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init of Authenticate");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");
            psAuthenticateUser = connection.prepareStatement("select * from users_0016 where userName=? and password=?");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Failed to initialize database");
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy of Authenticate");
        try {
            if (psAuthenticateUser != null)
                psAuthenticateUser.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("doPost of Authenticate");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        try {
            psAuthenticateUser.setString(1, userName);
            psAuthenticateUser.setString(2, password);

            try (ResultSet result = psAuthenticateUser.executeQuery()) {
                if (result.next()) {
                    response.sendRedirect("Category"); // Category URL
                } else {
                    out.println("<font color='red'>Authentication failure</font>");
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
