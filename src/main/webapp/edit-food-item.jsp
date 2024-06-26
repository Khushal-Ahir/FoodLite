<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="database.FoodItem"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Food Item</title>
<style type="text/css">

img {
	border-radius: 20px;
	box-shadow: 0 0 10px lightgray;
}

.btn {
	padding: 10px 0;
	width: 80%;
	display: flex;
	justify-content: space-around;
	align-content: center;
}

.btn button {
	padding: 10px 30px;
	background: chocolate;
	border: none;
	border-radius: 10px;
	font-size: 15px;
	color: white;
}
</style>

</head>
<body>
	<% FoodItem item = (FoodItem) request.getAttribute("foodItems"); %>
	<section>
		<form action="edit-food-item" method="post" enctype="multipart/form-data">
		<div class="item">Id : <input type="text" name="id" value="<%= item.getId() %>" readonly="readonly"></div><br>
		
		<div class="item">Name : <input type="text" name="name" value="<%= item.getName()%>"></div><br> 
		
		<div class="item">Cost : <input	type="text" name="price" value="<%= item.getPrice()%>"></div><br> 
		
		<div class="item">type : <input type="radio" name="type" value="veg" <%= item.getType().equals("veg") ? "checked='checked'" : "" %>>VEG
		<input type="radio" name="type" value="non-veg" <%= item.getType().equals("non-veg") ? "checked='checked'" : ""%>>NON-VEG</div> <br>
		
		<div class="item">Stock : <input type="number"	name="stock" value="<%= item.getStock()%>"></div> <br> 
		
		<div class="item">Picture : <input type="file"	name="image"></div> 
		<div class="item img">
			<img src="data:image/jpeg;base64,<%= Base64.encodeBase64String(item.getImage()) %>" alt="<%= item.getName()%>" height="150px" width="150px]">
		</div>
		<br>
		
		<div class="btn">
		<button>Update</button>
		</div>
	</form>
	</section>

</body>
</html>