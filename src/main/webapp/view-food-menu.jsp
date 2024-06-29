<%@page import="database.CartItem"%>
<%@page import="database.Customer"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="database.FoodItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.view-fooditems {
	display: grid;
	grid-template-columns: repeat(4, 24%);
	justify-content: space-around;
	padding: 0 50px;
}

.fodditem-list {
	border: 1px solid chocolate;
	border-radius: 20px;
	display: grid;
	justify-content: space-evenly;
	padding: 10px 0;
	margin: 10px 0;
}

img {
	border-radius: 20px;
	box-shadow: 0 0 10px lightgray;
	display: flex;
	justify-content: center;
	align-items: center;
}

.item-details {
	text-align: center;
	padding: 10px 0;
}

.btn {
	padding: 10px 0;
	width: 100%;
	display: flex;
	justify-content: space-around;
	align-items: center;
}

.btn button {
	padding: 10px 30px;
	background: chocolate;
	border: none;
	border-radius: 10px;
	font-size: 15px;
	color: white;
	cursor: pointer;
}
</style>
</head>
<body style="background: lavender">
	<%
	List<FoodItem> list = (List<FoodItem>) request.getAttribute("list");
	Customer customer = (Customer) session.getAttribute("customer");
	List<CartItem> cartItems = customer.getCart().getCartItems();
	%>
	<section>
		<div class="view_items">
			<div
				style="display: flex; justify-content: flex-end; align-items: center; width: 100%">
				<a href="hotel_dashboard.html">Home</a>
			</div>
			<div class="view-fooditems">
				<%
				for (FoodItem item : list) {
				%>
				<div class="fodditem-list">
					<div class="image">
						<img alt="<%=item.getName()%>"
							src="data:image/jpeg;base64,<%=Base64.encodeBase64String(item.getImage())%>"
							height="300px" width="330px">
					</div>
					<div class="item-details">
						<div class="hotel-detail">
							<h2><%=item.getHotel().getHotel_name()%></h2>
						</div>
						<div class="item-detail">
							<h3><%=item.getName()%>
								&nbsp;
								<%=item.getType()%></h3>
							<h3>
								Cost :
								<%=item.getPrice()%></h3>
							<h3></h3>
						</div>
						<div class="btn">
							<a href="delete-from-cart?id=<%= item.getId()%>"><button>-</button></a>
							<p>
								<%
								boolean flag = true;
								for (CartItem cartItem : cartItems) {
									if (cartItem.getName().equals(item.getName())) {
								%><%=cartItem.getQuantity()%>
								<%
								flag = false;
								break;
								}
								}
								if (flag) { %>0 <% } %>
							</p>
							<a href="add-to-cart?id=<%=item.getId()%>"><button>+</button></a>
							<a href=""><button>Add To Cart</button></a>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
		</div>
		<div class="btn">
			<a href="view-hotel"><button>Back</button></a>
		</div>
	</section>
</body>
</html>