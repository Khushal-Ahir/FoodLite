package foodlite;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FoodLitedao;
import database.Customer;
import database.FoodItem;

@WebServlet("/view-food-menu")
public class ViewFoodMenu extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		FoodLitedao fld = new FoodLitedao();
		HttpSession session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
//			List<FoodItem> list =  fld.fetchFoodByCustomer(customer.getId());
		int id = Integer.parseInt(req.getParameter("id"));
		List<FoodItem> list = fld.fetchFoodByHotel(id);

		if (list.isEmpty()) {
			resp.getWriter().print("<h1> No Food Item </h1>");
			req.getRequestDispatcher("view-hotel").include(req, resp);
		} else {
			req.setAttribute("list", list);
			req.getRequestDispatcher("view-food-menu.jsp").include(req, resp);
		}
	}
}
