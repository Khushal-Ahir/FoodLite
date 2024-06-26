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

@WebServlet("/hotel_signup")
public class HSignUp extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("hotel_signup.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String un = req.getParameter("username");
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		String hotel_name= req.getParameter("hotel_name");
		String address = req.getParameter("address");
		
		Hotel hotel = new Hotel(un, email, pass, hotel_name, address);
		FoodLitedao fld = new FoodLitedao();

		List<Hotel> list = fld.hotelByEmail(email);
		if (list.isEmpty()) {
			fld.saveHotel(hotel);
			
			resp.getWriter().print("<h1> YOur Account created successfully </1h>");
			req.getRequestDispatcher("hotel_login.html").forward(req, resp);
		}
		else {
			resp.getWriter().print("<h1 align='center' style='color: red'> Account is Already Exists "+email+"</h1>");
			req.getRequestDispatcher("hotel_login.html").include(req, resp);
		}
	}
}
