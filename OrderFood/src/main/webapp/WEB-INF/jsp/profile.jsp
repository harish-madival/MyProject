<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<title> ${title}</title>
<meta charset="UTF-8">
<%@include file="base.jsp" %>
</head>
<body>
	<header>
		<%@include file="nav.jsp"%>
	</header>
	
	<h1>Profile</h1>
	name= ${user.username}
</body>
</html>