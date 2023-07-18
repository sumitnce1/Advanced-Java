package org.sumitpayment.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Payment")
public class Payment extends HttpServlet {
	Connection connection;
    PaymentDAO paymentDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext application = getServletContext();
        connection = (Connection) application.getAttribute("globalConnection");
        paymentDAO = new PaymentDAOImpl(connection);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }
        String cardNo = request.getParameter("cardNo");
        String expiry = request.getParameter("expiry");
        double total = Double.parseDouble(request.getSession().getAttribute("total").toString());

        // Call the transact method to process the payment
        boolean paymentSuccessful = paymentDAO.transact(cardNo, expiry, total);

        if (paymentSuccessful) {
            out.println("<html><body>");
            out.println("<h2>Payment Successful</h2>");
            out.println("<p>Thank you for your purchase!</p>");
            
            out.println("</body></html>");
        } else {
            boolean reverseSuccessful = paymentDAO.reverseTransaction(cardNo, LocalDate.now().toString(), total, "Failed");

            if (reverseSuccessful) {
                out.println("<html><body>");
                out.println("<h2>Payment Failed</h2>");
                out.println("<p>Sorry, there was an issue with your payment. The transaction has been reversed. Please check the amount in your card and try again.</p>");
                out.println("</body></html>");
            } else {
                out.println("<html><head> <style>a:link, a:visited { background-color: #8A2BE2; color: white; padding: 10px 20px; text-decoration: none; }</style> <body><div style='text-align: center; margin-top: 40vh;'><body>");
                out.println("<h2 style='color: red;'>Payment Failed</h2>");
                out.println("<p>Sorry, there was an issue with your payment. Please contact customer support for assistance.</p>"  + "<h5><a href='Checkout'>Click Here To Checkout</a></h5>");
                out.println("</head></body></html>");                              
            }
        }
    }
}