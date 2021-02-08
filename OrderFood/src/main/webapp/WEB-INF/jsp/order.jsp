<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">

/*
Foods menu
*/
.food-menu-card {
	padding: 30px 20px;
	background-color: black;
	border-radius: 8px;
}

.food-menu-card .media {
	margin-bottom: 20px;
}

.food-menu-card .media .img-fluid {
	border-radius: 3px;
	width: 80px;
}

.food-menu-card .media .d-flex {
	position: relative;
}

.food-menu-card .media .d-flex:before {
	content: "";
	position: absolute;
	left: auto;
	right: 0;
	width: 100%;
	height: 1px;
	background-color: rgba(255, 255, 255, 0.2);
	top: 50%;
}

.food-menu-card .media h3 {
	position: relative;
	display: inline-block;
	margin-bottom: 0;
	padding-right: 1.07143rem;
	background-color: #000;
	z-index: 2;
}

.food-menu-card .media strong {
	background-color: #000;
	display: block;
	padding-left: 15px;
	z-index: 2;
	position: relative;
}

.tabs-menu-nav>li>a {
	color: #fff;
	display: block;
	font-size: 16px;
	border-bottom: 3px solid transparent;
	opacity: 0.5;
}

.tabs-menu-nav>li>a.active {
	opacity: 1;
	border-bottom-color: #db2e2e;
}

.media-body, p {
	color: #999;
}
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