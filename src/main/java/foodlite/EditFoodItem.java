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

@WebServlet("/edit-food-item")
@MultipartConfig
public class EditFoodItem extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("hotel") != null) {
			
			int id = Integer.parseInt(req.getParameter("id"));
			FoodLitedao fld = new FoodLitedao();
			FoodItem foodItems = fld.fetchFoodById(id);

			req.setAttribute("foodItems", foodItems);
			req.getRequestDispatcher("edit-food-item.jsp").forward(req, resp);

		} else {
			resp.getWriter().print("<h1> Invalid Session..! Go to Login Page</h1>");
			req.getRequestDispatcher("hotel_signup").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("hotel") != null) {
			
			String name = req.getParameter("name");
			String type = req.getParameter("type");
			int id = Integer.parseInt(req.getParameter("id"));
			int stock = Integer.parseInt(req.getParameter("stock"));
			double price = Double.parseDouble(req.getParameter("price"));

			Hotel hotel = (Hotel) req.getSession().getAttribute("hotel");
			
			Part part = req.getPart("image");
			byte[] image = new byte[part.getInputStream().available()];
			part.getInputStream().read(image);
			
			FoodItem foodItem = new FoodItem();
			foodItem.setId(id);
			foodItem.setName(name);
			foodItem.setPrice(price);
			foodItem.setStock(stock);
			foodItem.setType(type);
			foodItem.setHotel(hotel);
			
			FoodLitedao fld = new FoodLitedao();

			if (image.length != 0) {				
				foodItem.setImage(image);
			}
			else {				
				foodItem.setImage(fld.fetchFoodById(id).getImage());
			}

			fld.updateFoodItem(foodItem);

			resp.getWriter().print("<h1 align='center' style='color:green'>Food Item Updated Success</h1>");
			req.getRequestDispatcher("view-food-item").include(req, resp);
		} else {
			resp.getWriter().print("<h1> Invalid Session..! Go to Login Page</h1>");
			req.getRequestDispatcher("hotel_signup").forward(req, resp);
		}
	}
}
