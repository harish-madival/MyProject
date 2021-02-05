<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="base.jsp"%>
</head>
<body>
	<%@include file="nav.jsp" %>
	<div clas="container">
		<div class="row mt-2">
			<div class="col-md-8 offset-md-2">
				<form action="addfood" method="post">
				<div class="form-group">
					Food Name:<input type="text" name="itemname" class="form-control"/>
				</div>
				<div class="form-group">
					Price:<input type="text" name="price" class="form-control"/>
				</div>
				<div class="form-group">
					<select name="type">
						<option value="Veg">Veg</option>
						<option value="Non-Veg">Non-Veg</option>
					</select>
				</div>
				<div class="form-group">
					<input class="btn btn-primary" type="submit" />
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>