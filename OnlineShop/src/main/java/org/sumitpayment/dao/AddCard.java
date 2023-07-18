package org.sumitpayment.dao;

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

@WebServlet("/AddCard")
public class AddCard extends HttpServlet {
    Connection connection;
    PaymentDAO paymentDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext application = getServletContext();
        connection = (Connection) application.getAttribute("globalConnection");
        paymentDAO = new PaymentDAOImpl(connection);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String cardNo = request.getParameter("cardNo");
        String expiry = request.getParameter("expiry");
        String balance = request.getParameter("balance");
        String status = request.getParameter("status");

        boolean result = paymentDAO.addCard(cardNo, expiry, balance, status);

        if (result) {            
            out.println("<html><head><style>a:link, a:visited { background-color: #8A2BE2; color: white; padding: 10px 20px; text-decoration: none; }</style><body><div style='text-align: center; margin-top: 40vh;'>"
                    + "<h3 style='color: green;'>Card added successfully</h3>"
            		+ "<h5><a href='login.html'>Click Here To Login</a></h5>"
                    + "</div></body></head></html>");
        } else {
            out.println("<html><head><style>a:link, a:visited { background-color: #8A2BE2; color: white; padding: 10px 20px; text-decoration: none; }</style><body><div style='text-align: center; margin-top: 40vh;'>"
                    + "<h3 style='color: red;'>Oops! Failed to add card. Please try again.</h3>"
                    + "<h5><a href='AddCard.html'>Click Here AddCard</a></h5>"
                    + "</div></body></head></html>");
        }
    }
}