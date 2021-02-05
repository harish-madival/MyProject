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

	<div>${buystatus}</div>
	<c:forEach items="${carteddata }" var="cd">
	<div class="card" style="width: 18rem;">
		<img class="card-img-top" src="..." alt="Card image cap">
		<div class="card-body">
			<h5 class="card-title"> ${cd.itemname}</h5>
			<p class="card-text">Price: ${cd.price}</p>
			<a href="${pageContext.request.contextPath }/selectveg" class="btn btn-primary">Order More</a>
			<a href="${pageContext.request.contextPath }/delete/${cd.id}" class="btn btn-gray">Delete</a>
		</div>
	</div>
	</c:forEach>
	<button class="btn btn-dark mt-3"> <a href="buy">BuyOrder</a>  </button>
	
</body>
</html>