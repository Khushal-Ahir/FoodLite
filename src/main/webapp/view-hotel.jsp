<%@page import="database.Hotel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels"); %>	
	
	<main>
		<section>
			<div class="main-container">
				<div class="main-content">
				<h1>Select Hotel</h1>
					<% for(Hotel hotel : hotels) {%>
						<div class="content">
							<h2>Name : <%= hotel.getHotel_name() %></h2>
							<h2>Address : <%= hotel.getAddress() %></h2>
							<a href="view-food-menu?id=<%= hotel.getId()%>"><button>Select</button></a>
						</div>
					<% } %>
				</div>
			</div>
			<a href="cust-home"><button>Back</button></a>
		</section>
	</main>

</body>
</html>