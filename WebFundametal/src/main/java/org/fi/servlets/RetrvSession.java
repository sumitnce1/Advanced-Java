package org.fi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RetrvSession")
public class RetrvSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if(session == null) {
            out.println("No session for this user.");
            return;
        }

        out.println("User Name: " + session.getAttribute("userName"));
        out.println();
        out.println("Id: " + session.getId());
        out.println("Creation Time: " + new Date(session.getCreationTime()));
        out.println("Last Accessed Time: " + new Date(session.getLastAccessedTime()));
        out.println("Max Inactive Interval: " + session.getMaxInactiveInterval());
    }
}
