<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="base.jsp"%>
</head>
<body>
	<header>
		<%@include file="nav.jsp"%>
	</header>
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">OrderId</th>
				<th scope="col">Name</th>
				<th scope="col">Price</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ orderDetail}" var="ordereddta">
				<tr>
					<th>${ordereddta.orderid}</th>
					<td>${ordereddta.cartItemName }</td>
					<td>${ordereddta.cartPrice }</td>
					
				</tr>
			</c:forEach>
			<tr>
				<th colspan="2">Total</th>
				<td> ${totalamount}
				<button class="btn btn-success">PayNow</button>
				</td>
			</tr>
		</tbody>
	</table>

</body>
</html>