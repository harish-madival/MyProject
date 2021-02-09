<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">

</style>
<%@include file="base.jsp"%>

</head>
<body>
	<header>
		<%@include file="nav.jsp"%>
	</header>
	
	<div class="container">
		<div class="mt-4">
			<h2 class="text-center">Our Food Menu</h2>
		</div>
		<div class="food-menu-card mt-2">
			<div>
				<!-- Nav tabs -->
				<ul class="nav  tabs-menu-nav justify-content-center mb30"
					role="tablist">
					<li class="nav-item" role="presentation"><a
						class="nav-link active show"
						href="selectveg">Veg</a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link active show"
						href="selectnonveg">Non-Veg</a></li>
				</ul>

				<!-- Tab panes -->

				<div class="row mt-3">
					<div class="col-md-6 offset-md-3 mb20">
						<table class="table table-hover text-white">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col">Item Name</th>
									<th scope="col">Price</th>
									<th scope="col">Quantity</th>
									<th scope="col"></th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="list">
									<form action="cart/${list.id}">

										<tr>
											<th scope="row"><img alt="img1" src=""></th>
											<td>${list.itemname}</td>
											<td>${list.price}</td>
											<td><select name="quantity" id="quantity">
													<option value=1>1</option>
													<option value=2>2</option>
													<option value=3>3</option>
													<option value=4>4</option>
													<option value=5>5</option>
													<option value=6>6</option>
													<option value=7>7</option>
													<option value=8>8</option>

											</select></td>
											<td><input type="submit" value="Add"></td>
										</tr>
									</form>

								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>