package foodlite;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodLitedao;
import database.Hotel;

@WebServlet("/hotel_login")
public class HLogin extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("hotel_login.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("email");
		String pass = req.getParameter("password");
		
		FoodLitedao fld = new FoodLitedao();
		
		List<Hotel> list= fld.hotelByEmail(mail);
		
		if(list.isEmpty()) {
			resp.getWriter().print("<h1 align='center' style='color: red'> Email is Invalid or Not Register </h1>");
			req.getRequestDispatcher("hotel_login.html").include(req, resp);
		}
		else if(pass.equals(list.get(0).getPassword())) {
			req.getSession().setAttribute("hotel", list.get(0));
			req.getRequestDispatcher("hotel_dashboard.html").include(req, resp);
		}
		else {
			resp.getWriter().print("<h1 align='center' style='color: red'> Your Password is Invalid </h1>");
			req.getRequestDispatcher("customer_login.html").include(req, resp);
		}
	}
}
