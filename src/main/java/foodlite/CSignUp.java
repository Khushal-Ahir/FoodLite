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

@WebServlet("/cust_signup")
public class CSignUp extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/customer_signup.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String un = req.getParameter("username");
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		String cnf_pass = req.getParameter("cnf_password");
		String address = req.getParameter("address");
		
		Customer customer = new Customer(un, email, pass, cnf_pass, address);
		
		FoodLitedao fld = new FoodLitedao();
		
		List<Customer> list=fld.custByEmail(email);
		if(list.isEmpty()) {
			fld.saveCustomer(customer);
			
			resp.getWriter().print("<h1 align='center' style='color: green'> Account is Created Success</h1>");
			req.getRequestDispatcher("customer_login.html").include(req, resp);
		}
		else {
			resp.getWriter().print("<h1 align='center' style='color: red'> Account is Already Exists - "+email+" </h1>");
			req.getRequestDispatcher("customer_signup.html").include(req, resp);
		}
	}
}
