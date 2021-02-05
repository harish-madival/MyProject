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
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<th scope="row"> ${list.id}</th>
								<td value="Veg Biryani"> ${list.itemname}</td>
								<td value="150">${list.price}</td>
								<td><a href=<% request.getContextPath(); %>"cart/${list.id}">
										<button class="btn btn-outline-primary" >Add</button>
								</a></td>
							</tr>
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
								<th scope="col"></th>
							</tr>
						</thead>
						
						<tbody>
						
							<c:forEach items="${list}" var="list">
							<tr>
								<th scope="row"> ${list.id}</th>
								<td value="Veg Biryani"> ${list.itemname}</td>
								<td value="150">${list.price}</td>
								<td><a href=<% request.getContextPath(); %>"cart/${list.id}">
										<button class="btn btn-outline-primary" >Add</button>
								</a></td>
							</tr>
						</c:forEach>						
						</tbody>
					</table>

				</c:if>
				

			</div>
		</div>
	</div>
</body>
</html>