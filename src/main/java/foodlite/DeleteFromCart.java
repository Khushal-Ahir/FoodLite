package foodlite;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodLitedao;
import database.Cart;
import database.CartItem;
import database.Customer;
import database.FoodItem;

@WebServlet("/delete-from-cart")
public class DeleteFromCart extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		if (customer == null) {

			resp.getWriter().print("<h1> Invalid Session </h1>");
			req.getRequestDispatcher("cust_loginn").include(req, resp);

		} else {

			int id = Integer.parseInt(req.getParameter("id"));
			FoodLitedao fld = new FoodLitedao();

			FoodItem item = fld.fetchFoodById(id);

			Cart cart = customer.getCart();
			List<CartItem> cartItems = cart.getCartItems();

			CartItem cartitem = null;
			for (CartItem cartItem2 : cartItems) {
				if (cartItem2.getName().equals(item.getName())) {
					cartitem = cartItem2;
					break;
				}
			}
			if (cartitem == null) {
				resp.getWriter().print("<h1> No Cart Item is There </h1>");
				req.getRequestDispatcher("view-food-menu").include(req, resp);
			} else {
				cartitem.setQuantity(cartitem.getQuantity() - 1);
				cartitem.setPrice(cartitem.getPrice() - item.getPrice());
				fld.updateCartItem(cartitem);

				cart.setTotalPrice(cart.getCartItems().stream().mapToDouble(x -> x.getPrice()).sum());
				fld.updateCart(cart);

				if (cartitem.getQuantity() == 0) {
					
					CartItem item2 = fld.fetchCartItemById(cartitem.getId());
					cart.getCartItems().remove(item2);
					fld.updateCart(cart);
					fld.deleteCartItem(item2);
				}
				
				item.setStock(item.getStock() + 1);
				fld.updateFoodItem(item);

				req.getSession().setAttribute("customer", fld.custByEmail(customer.getEmail()).get(0));

				resp.getWriter().print("<h1> Item Rrmove from cart </h1>");
				req.getRequestDispatcher("view-food-menu").include(req, resp);
			}
		}
	}
}
