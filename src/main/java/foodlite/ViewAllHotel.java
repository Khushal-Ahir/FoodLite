package foodlite;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodLitedao;
import database.Customer;
import database.Hotel;

@WebServlet("/view-hotel")
public class ViewAllHotel extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FoodLitedao fld = new FoodLitedao();
		List<Hotel> hotels = fld.fetchHotel();

		if (hotels.isEmpty()) {
			resp.getWriter().print("<h1> No item Now </h1>");
			req.getRequestDispatcher("cust-home").forward(req, resp);
		} else {
			req.setAttribute("hotels", hotels);
			req.getRequestDispatcher("view-hotel.jsp").include(req, resp);
		}
	}
}
