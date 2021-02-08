<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%@include file="base.jsp"%>
</head>
<body>
	<header>
		<%@include file="nav.jsp"%>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-10 col-md-offset-1">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Product</th>
							<th>Quantity</th>
							<th class="text-center">Price</th>
							<th class="text-center">Total</th>
							<th> </th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${carteddata }" var="cd">
							<tr>
								<td class="col-sm-8 col-md-6">
									<div class="media">
										<a class="thumbnail pull-left" href="#"> <img
											class="media-object mr-2"
											src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png"
											style="width: 72px; height: 72px;">
										</a>
										<div class="media-body">
											<h4 class="media-heading">
												<a href="#">${cd.itemname}</a>
											</h4>
											
											<span>Status: </span><span class="text-success"><strong>Pending</strong></span>
										</div>
									</div>
								</td>
								<td class="col-sm-1 col-md-1" style="text-align: center">${cd.quantity}</td>
								<td class="col-sm-1 col-md-1 text-center"><strong>$${cd.price}</strong></td>
								<td class="col-sm-1 col-md-1 text-center"><strong>$${cd.totalprice}</strong></td>
								<td class="col-sm-1 col-md-1">
									<a href="${pageContext.request.contextPath }/delete/${cd.id}"><button type="button" class="btn btn-danger">
										<span class="glyphicon glyphicon-remove"></span> Remove
									</button></a>
								</td>
							</tr>
						</c:forEach>


						<tr>
							<td> </td>
							<td> </td>
							<td> </td>
							<td><h5>Subtotal</h5></td>
							<td class="text-right"><h5>
									<strong>${subtotal}</strong>
								</h5></td>
						</tr>
						
						
						<tr>
							<td> </td>
							<td> </td>
							<td> </td>
							<td>
								<a href="selectveg"><button type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-shopping-cart"></span>
									Continue Shopping
								</button></a>
							</td>
							<td>
								<a href="buy"><button type="button" class="btn btn-success">
									Checkout <span class="glyphicon glyphicon-play"></span>
								</button></a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>