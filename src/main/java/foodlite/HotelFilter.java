package foodlite;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import database.Hotel;

@WebFilter({})
public class HotelFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Hotel hotel = (Hotel) req.getSession().getAttribute("hotel");
		
		if(hotel == null) {
			response.getWriter().print("<h1>Invalid Session </h1>");
			req.getRequestDispatcher("hotel_login").include(req, response);
		}else {
			chain.doFilter(request, response);
		}
	}
}
