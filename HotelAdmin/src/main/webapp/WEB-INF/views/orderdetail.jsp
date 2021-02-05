<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="base.jsp"%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="nav.jsp" %>
<c:forEach items="${ordereddata}" var="od">
	 <div>${od.orderid}</div>  
	 <div>${od.cartItemName}</div>
	 <div>${od.cartPrice}</div>
</c:forEach>
</body>
</html>