package org.sumitshop.servlets;

import java.io.IOException;
import java.util.Iterator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.sumit.cart.Cart;
import org.sumit.servlets.CartItem;

@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            response.sendRedirect("ListCart");
            return;
        }

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int productId = Integer.parseInt(request.getParameter("productId"));

        Iterator<CartItem> iterator = cart.allItems();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getCategoryId() == categoryId && item.getProductId() == productId) {
                iterator.remove();
                break;
            }
        }

        response.sendRedirect("ListCart");
    }
}
