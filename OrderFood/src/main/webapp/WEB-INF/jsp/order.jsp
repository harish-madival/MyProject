<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body {
	margin-top: 20px;
}
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
	<%-- <div class="container">
		<div class="col-md-8 offset-md-2 ">
			<h1 class="mt-2">Welcome to User</h1>

			<div class="list-group">
				<a href="selectveg"
					class="list-group-item list-group-item-action active">Veg</a> <a
					href="selectnonveg" class="list-group-item list-group-item-action">Non-Veg</a>

			</div>
			<div class="mt-2" style="background-color: lightgray">

				<c:if test="${select=='veg'}">				
					<table class="table table-sm">
						<thead>
							<tr>
								<th scope="col">Sl No.</th>
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
									<th scope="row">${list.id}</th>
									<td>${list.itemname}</td>
									<td>${list.price}</td>
									<td><select name="quantity" id="quamtity">
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

				</c:if>
				<c:if test="${select=='nonveg'}">
					<table class="table table-sm">
						<thead>
							<tr>
								<th scope="col">Sl No.</th>
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
									<th scope="row">${list.id}</th>
									<td>${list.itemname}</td>
									<td>${list.price}</td>
									<td><select name="quantity" id="quamtity">
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

				</c:if>


			</div>
		</div>
	</div> --%>
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
						class="nav-link active show" href="#veg"
						aria-controls="veg" role="tab" data-toggle="tab"
						aria-selected="true">Veg</a></li>
					<li class="nav-item" role="presentation"><a class="nav-link"
						href="#nonveg" aria-controls="nonveg" role="tab" data-toggle="tab"
						aria-selected="false"> Non-Veg</a></li>
					
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane fade active show"
						id="veg">
						<div class="row">
						<c:forEach items="${list}" var="list">
							<div class="col-md-6 mb20">
								<article class="media">
									<!-- Article Image -->
									<a class="g-width-100" href="#"> <img
										class="img-fluid mr-4" width="80"
										src="http://bootstraplovers.com/assan-kit-3.8/bootstrap4/website-templates/classic-template/html/images/resto/food1.jpg"
										alt="Menu title">
									</a>
									<!--Image -->
									<!--Content -->
									
									<div class="media-body align-self-center g-pl-10">
										<div class="d-flex justify-content-between mb10">
											<h3
												class="align-self-center text-capitalize mb0 h6 text-white font400">${list.itemname}
												title</h3>

											<div class="align-self-center">
												<strong class="text-white font700">${list.price}</strong>
											</div>
										</div>

										<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
											adipiscing elit.</p>
									</div>
									
									<!--/Content -->
								</article>
								<!--/Article -->


							</div>
							<div class="col-md-6 mb20">
								<article class="media">
									<!-- Article Image -->
									<a class="g-width-100" href="#"> <img
										class="img-fluid mr-4" width="80"
										src="http://bootstraplovers.com/assan-kit-3.8/bootstrap4/website-templates/classic-template/html/images/resto/food6.jpg"
										alt="Menu title">
									</a>
									<!--Image -->
									<!--Content -->
									<c:forEach items="${list}" var="list"></c:forEach>
									<div class="media-body align-self-center g-pl-10">
										<div class="d-flex justify-content-between mb10">
											<h3
												class="align-self-center text-capitalize mb0 h6 text-white font400">Menu
												title</h3>

											<div class="align-self-center">
												<strong class="text-white font700">$1.20</strong>
											</div>
										</div>

										<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
											adipiscing elit.</p>
									</div>
									
									<!--/Content -->
								</article>
								<!--/Article -->

							</div>
						</div>
						</c:forEach>
					</div>
					<div role="tabpanel" class="tab-pane fade" id="nonveg">
						<div class="row">
							<div class="col-md-6 mb20">
								<article class="media">
									<!-- Article Image -->
									<a class="g-width-100" href="#"> <img
										class="img-fluid mr-4" width="80"
										src="http://bootstraplovers.com/assan-kit-3.8/bootstrap4/website-templates/classic-template/html/images/resto/food10.jpg"
										alt="Menu title">
									</a>
									<!--Image -->
									<!--Content -->
									<div class="media-body align-self-center g-pl-10">
										<div class="d-flex justify-content-between mb10">
											<h3
												class="align-self-center text-capitalize mb0 h6 text-white font400">Menu
												title</h3>

											<div class="align-self-center">
												<strong class="text-white font700">$1.20</strong>
											</div>
										</div>

										<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
											adipiscing elit.</p>
									</div>
									<!--/Content -->
								</article>
								<!--/Article -->

							</div>
							<div class="col-md-6 mb20">
								<article class="media">
									<!-- Article Image -->
									<a class="g-width-100" href="#"> <img
										class="img-fluid mr-4" width="80"
										src="http://bootstraplovers.com/assan-kit-3.8/bootstrap4/website-templates/classic-template/html/images/resto/food5.jpg"
										alt="Menu title">
									</a>
									<!--Image -->
									<!--Content -->
									<div class="media-body align-self-center g-pl-10">
										<div class="d-flex justify-content-between mb10">
											<h3
												class="align-self-center text-capitalize mb0 h6 text-white font400">Menu
												title</h3>

											<div class="align-self-center">
												<strong class="text-white font700">$1.20</strong>
											</div>
										</div>

										<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
											adipiscing elit.</p>
									</div>
									<!--/Content -->
								</article>
								<!--/Article -->


							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>