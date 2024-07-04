package foodlite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.FoodLitedao;
import database.FoodItem;
import database.Hotel;

@WebServlet("/add-item")
@MultipartConfig
public class AddFoodItem extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("add-food-item.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		String type = req.getParameter("type");
		int stock = Integer.parseInt(req.getParameter("stock"));

		Part part = req.getPart("image");
		byte[] image = new byte[part.getInputStream().available()];
		part.getInputStream().read(image);

		Hotel hotel = (Hotel) req.getSession().getAttribute("hotel");

		FoodItem fi = new FoodItem(name, price, type, stock, image, hotel);

		FoodLitedao fld = new FoodLitedao();
		fld.saveFoodItem(fi);

		resp.getWriter().print("<h1 align='center' style='color:green'>Food Item Added Success</h1>");
		req.getRequestDispatcher("hotel_dashboard.html").include(req, resp);
	}
}
