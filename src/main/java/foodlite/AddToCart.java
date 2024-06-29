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

@WebServlet("/add-to-cart")
public class AddToCart extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		if (customer == null) {
			resp.getWriter().print("<h1 align='center' style='color:red'>Invalid Session</h1>");
			req.getRequestDispatcher("customer-login.html").include(req, resp);
		} else {
			int id = Integer.parseInt(req.getParameter("id"));
			FoodLitedao fld = new FoodLitedao();
			FoodItem item = fld.fetchFoodById(id);

			if (item.getStock() > 0) {
				Cart cart = customer.getCart();
				List<CartItem> cartItems = cart.getCartItems();

				boolean flag = true;
				for (CartItem cartItem : cartItems) {
					if (cartItem.getName().equals(item.getName())) {
						cartItem.setPrice(cartItem.getPrice() + item.getPrice());
						cartItem.setQuantity(cartItem.getQuantity() + 1);
						flag = false;
					}
				}

				if (flag) {
					CartItem cartItem = new CartItem();
					cartItem.setImage(item.getImage());
					cartItem.setName(item.getName());
					cartItem.setPrice(item.getPrice());
					cartItem.setType(item.getType());
					cartItem.setQuantity(1);
					cartItems.add(cartItem);
				}

				cart.setTotalPrice(cart.getCartItems().stream().mapToDouble(x -> x.getPrice()).sum());

				customer.setCart(cart);
				fld.updateCustomer(customer);
				
				item.setStock(item.getStock()-1);
				fld.updateFoodItem(item);
				
				req.getSession().setAttribute("customer", fld.custByEmail(customer.getEmail()).get(0));
				
				resp.getWriter().print("<h1 align='center' style='color:green'>Item Added to Cart</h1>");
				req.getRequestDispatcher("view-hotel").include(req, resp);

			} else {
				resp.getWriter().print("<h1 align='center' style='color:red'>Out of Stock</h1>");
				req.getRequestDispatcher("view-food-menu").include(req, resp);
			}
		}
	}
}
