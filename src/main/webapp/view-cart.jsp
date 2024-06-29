<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="database.Cart"%>
<%@page import="database.CartItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.cart-content{
	display: grid;
	gap: 30px;
}
.cart-content h1{
	text-align: center;
	color: seagreen;
	font-weight: bolder;
}
.cart-details {
	display: flex;
	justify-content: space-around;
	align-items: center;
	width: 50%;
	margin: 0 auto;
}
.cart-details .img {
  width: 20%;
  gap: 50px;
}
.cart-details img{
	width: 100%;
  	border-radius: 10px;
  	height: 140px;
}
.total-price{
	width: 50%;
	margin: 0 auto;
	display: flex;
	justify-content: space-around;
	align-items: center;
}
</style>
</head>
<body style="background: linen">
	<%
	Cart cart = (Cart) request.getAttribute("cart");
	%>
	<main>
		<section>
			<div class="main-cart-container">
				<div class="cart-content">
				<h1>Your Order is : </h1>
					<%
					for (CartItem item : cart.getCartItems()) {
					%>
					<div class="cart-details">
						<div class="img">
							<img alt="<%=item.getName()%>"
								src="data:image/jpeg;base64,<%=Base64.encodeBase64String(item.getImage())%>">
						</div>
						<div class="cart-details">
							<h2><%=item.getName()%> &nbsp &nbsp</h2>
							<h2><%=item.getQuantity()%>	x <%=item.getPrice() / item.getQuantity()%> = <%=item.getPrice()%></h2>
						</div>
					</div>
					<%
					}
					%>
					<div class="total-price">
						<h1>Total Price : <%=cart.getTotalPrice()%></h1>
						<a href="place-order"><button>Place Order</button></a>
					</div>
				</div>
			</div>
		</section>
	</main>
</body>
</html>