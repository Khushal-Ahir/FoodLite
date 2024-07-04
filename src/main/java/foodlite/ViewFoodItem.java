package foodlite;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodLitedao;
import database.FoodItem;
import database.Hotel;

@WebServlet("/view-food-item")
public class ViewFoodItem extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Hotel hotel = (Hotel) req.getSession().getAttribute("hotel");
		FoodLitedao fld = new FoodLitedao();
		List<FoodItem> list = fld.fetchFoodByHotel(hotel.getId());

		if (list.isEmpty()) {
			resp.getWriter().print("<h1 align='center' style='color:red'>No Food Items Added Yet</h1>");
			req.getRequestDispatcher("hotel_dashboard.html").include(req, resp);
		} else {
			req.setAttribute("list", list);
			req.getRequestDispatcher("view-food-item.jsp").include(req, resp);
		}
	}
}
