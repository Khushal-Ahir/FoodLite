<%@page
	import="org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="database.Hotel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.main-content {
	display: grid;
	grid-template-columns: repeat(4, 24%);
	justify-content: space-around;
	padding: 0 50px;
}

.content {
	border: 1px solid chocolate;
	border-radius: 20px;
	display: grid;
	justify-content: space-evenly;
	padding: 10px 0;
	margin: 10px 0;
}
h1 {
  text-align: center;
  color: darkblue;
}
img {
	border-radius: 20px;
	box-shadow: 0 0 10px lightgray;
	display: flex;
	justify-content: center;
	align-items: center;
	width: 300px;
	height: 200px;
}

.hotel-details {
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
<body style="background: linen">
	<%
	List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
	%>

	<main>
		<section>
			<div class="main-container">
				<h1>Select Hotel</h1>
				<div class="main-content">
					<%
					for (Hotel hotel : hotels) {
					%>
					<div class="content">
						<div class="image">
							<img alt="<%=hotel.getHotel_name()%>"
								src="data:image/jpeg;base64,<%=Base64.encodeBase64String(hotel.getImage())%>">
						</div>
						<div class="hotel-details">
							<h2>
								Name :
								<%=hotel.getHotel_name()%></h2>
							<h2>
								Address :
								<%=hotel.getAddress()%></h2>
							<div class="btn">
								<a href="view-food-menu?id=<%=hotel.getId()%>"><button>Select</button></a>
							</div>
						</div>
					</div>
					<%
					}
					%>
				</div>
			</div>
			<div class="btn">
				<a href="cust-home"><button>Back</button></a>
			</div>
		</section>
	</main>

</body>
</html>