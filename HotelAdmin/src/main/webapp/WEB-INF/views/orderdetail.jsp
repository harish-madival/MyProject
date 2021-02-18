<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="base.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<header><%@include file="nav.jsp"%></header>
	<div class="container">
		<div class="row">
			<div class="span5">
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>OrderId</th>
							
							<th>Date registered</th>
							<th>Item Name</th>
							<th>Price</th>
							<th>Status</th>
							<th>User Id</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ordereddata}" var="orderedData">
							<tr>
								<td rowspan="">${orderedData.orderId}</td>
								
								<td>${orderedData.orderedDate}</td>
								<td>${orderedData.cartItemName }</td>
								<td>${orderedData.cartPrice }</td>
								<td><span class="label label-success">Pending</span></td>
								<td> ${orderedData.userId}</td>
								
							</tr>
						</c:forEach>


					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>