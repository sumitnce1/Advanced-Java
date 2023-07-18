package org.sumitshopuser.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ForgetPassword")
public class ForgetPassword extends HttpServlet {
    Connection connection;
    UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext application = getServletContext();
        connection = (Connection) application.getAttribute("globalConnection");
        userDAO = new UserDAOImpl(connection);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        

        boolean status = userDAO.changePassword(userName,password);

        if (status) {
            out.println("<html><head><style>a:link, a:visited { background-color: #8A2BE2; color: white; padding: 10px 20px; text-decoration: none; }</style><body><div style='text-align: center; margin-top: 40vh;'>"
                    + "<h3 style='color: green;'>Password reset successful. Check your email for the new password.</h3>"
                    + "<h5><a href='login.html'>Click Here To Login</a></h5>"
                    + "</div></body></head></html>");
        } else {
            out.println("<html><head><style>a:link, a:visited { background-color: #8A2BE2; color: white; padding: 10px 20px; text-decoration: none; }</style><body><div style='text-align: center; margin-top: 40vh;'>"
                    + "<h3 style='color: red;'>Oops! Password reset failed. User not found or incorrect old password.</h3>"
                    + "<h5><a href='ForgetPassword.html'>Click Here To Reset Password</a></h5>"
                    + "</div></body></head></html>");
        }
    }
}