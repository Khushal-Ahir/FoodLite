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

@WebServlet("/cust_login")
public class CLogin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("customer_login.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("email");
		String pass = req.getParameter("password");

		FoodLitedao fld = new FoodLitedao();

		List<Customer> list = fld.custByEmail(mail);
		
		if(list.isEmpty()) {
			resp.getWriter().print("<h1 align='center' style='color: red'> Your Email is Invalid or Not Exists </h1>");
			req.getRequestDispatcher("customer_login.html").include(req, resp);
		}
		else if(pass.equals(list.get(0).getPassword())) {
			req.getSession().setAttribute("customer", list.get(0));
			req.getRequestDispatcher("cust_dashboard.html").include(req, resp);
		}
		else {
			resp.getWriter().print("<h1 align='center' style='color: red'> Your Password is Invalid </h1>");
			req.getRequestDispatcher("customer_login.html").include(req, resp);
		}
	}
}
