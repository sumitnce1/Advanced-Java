package org.sumit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET request
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        double total = (Double) session.getAttribute("total");

        out.println("<html><head> <style>a:link, a:visited { background-color: #008000; color: white; padding: 10px 20px; text-decoration: none; }</style> <body><div style='text-align: center; margin-top: 20vh;'><body>");
        out.println("<h2> Welcome To Online Shop - <b>" + session.getAttribute("userName") + "</b>&nbsp; &nbsp; &nbsp; &nbsp; <a href='Logout'>Logout</a> &nbsp; &nbsp; &nbsp; <a href='ListCart'> Cart&#128722;</a><br/><br/> </h2>");
        out.println("<h2>Checkout</h2></hr>");
        out.println("Total: " + total);
        out.println("<br/><br/>");
        out.println("<form method='post' action='Payment'>");
        out.println("Card Number: <input type='text' name='cardNo'></br>");
        out.println("Expiry (mm/yyyy): <input type='text' name='expiry'></br>");
        out.println("<input type='submit' value='Pay'>");
        out.println("</form>");
        
        out.println("<h5><a href='ListCart'>Click Go To Cart</a></h5>");
        out.println("</body>");
        out.println("</head></html>");
    }
}