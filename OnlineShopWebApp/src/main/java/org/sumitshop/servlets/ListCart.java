package org.sumitshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.sumitshop.cart.Cart;
import org.sumitshop.cart.CartItem;

@WebServlet("/ListCart")
public class ListCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            out.println("<html><head> <style>a:link, a:visited { background-color: #00008B; color: white; padding: 10px 20px; text-decoration: none; }</style><body><div style='text-align: center; margin-top: 40vh;'> "
                    + "<h1 style='color: red;'>No products in your cart</h1> "
                    + "<h3><a href='Category'>Continue Shopping</a></h3>"
                    + "</body></head></html>");
            return;
        }

        out.println("<html>");
        out.println("<body>");
        out.println("<h2> Welcome To Online Shop - <b>" + session.getAttribute("userName") + "</b>&nbsp; &nbsp; &nbsp; &nbsp; <a href='Logout'>Logout</a> &nbsp; &nbsp; &nbsp; <a href='ListCart'> Cart&#128722;</a><br/><br/> </h2>");        
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>Category Id </th>");
        out.println("<th>Product Id </th>");
        out.println("<th>Product Name </th>");
        out.println("<th>Product Price </th>");
        out.println("<th>Product Image </th>");
        out.println("<th>Remove </th>");
        out.println("</tr>");

        Iterator<CartItem> iterator = cart.allItems();
        double total = 0.0;

        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            out.println("<tr>");
            out.println("<td>" + item.getCategoryId() + "</td>");
            out.println("<td>" + item.getProductId() + "</td>");
            out.println("<td>" + item.getProductName() + "</td>");
            out.println("<td>" + item.getProductPrice() + "</td>");
            out.println("<td><img src='Images/" + item.getProductImageUrl() + "' height='60px' width='60px' /></td>");
            out.println("<td><a href='RemoveFromCart?categoryId=" + item.getCategoryId() + "&productId=" + item.getProductId() + "'>Remove</a></td>");
            total += item.getProductPrice();
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<br/><br/>");
        out.println("Total: " + total);
        session.setAttribute("total", total);
        out.println("<br/><br/>");
        out.println("<h3 style='color: green;'><a href='category.jsp' style='text-decoration: none;'>Continue Shopping</a>&nbsp; &nbsp; &nbsp;<a href='Checkout' style='text-decoration: none;'>Checkout</a></h3>");
        out.println("</body>");
        out.println("</html>");
    }
}
