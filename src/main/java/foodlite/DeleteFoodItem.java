package foodlite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FoodLitedao;
import database.FoodItem;
import database.Hotel;

@WebServlet("/delete-fooditem")
public class DeleteFoodItem extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FoodLitedao fld = new FoodLitedao();

		int id = Integer.parseInt(req.getParameter("id"));
		FoodItem item = fld.fetchFoodById(id);

		fld.deleteFoodItem(item);

		resp.getWriter().print("<h1 style='color:green' align='center'>Deleted Success</h1>");
		req.getRequestDispatcher("view-food-item").include(req, resp);
	}
}
