package foodlite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Cart;
import database.Customer;

@WebServlet("/view-cart")
public class ViewCart extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		Cart cart = customer.getCart();
		if (cart.getCartItems().isEmpty()) {
			System.out.println("enter if first");
			resp.getWriter().print("<h1> Cart is Empty </h1>");
			req.getRequestDispatcher("view-food-menu").include(req, resp);
			System.out.println("enter if sec");
		} else {
			req.setAttribute("cart", customer.getCart());
			req.getRequestDispatcher("view-cart.jsp").forward(req, resp);
		}
	}
}
