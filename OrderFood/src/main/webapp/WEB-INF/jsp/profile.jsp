<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<title>${title}</title>
<meta charset="UTF-8">
<%@include file="base.jsp"%>
</head>
<body>
<header>
	<%@include file="nav.jsp"%>
</header>

<hr>
<div class="container bootstrap snippet">
	<div class="row">
		<div class="col-sm-10">
			<h1>User name</h1>
		</div>
		<div class="col-sm-2">
			<a href="/users" class="pull-right"><img title="profile image"
				class="img-circle img-responsive"
				src="http://www.gravatar.com/avatar/28fd20ccec6865e2d5f0e1f4446eb7bf?s=100"></a>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3">

			<div class="text-center">
				<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"
					class="avatar img-circle img-thumbnail" alt="avatar">
			</div>
			</hr>
			<br>


			<div class="panel panel-default">
				<div class="panel-heading">Social Media</div>
				<div class="panel-body">
					<i class="fab fa-facebook"></i> <i class="fab fa-instagram"></i> <i
						class="fab fa-twitter"></i> <i class="fab fa-google"></i>
				</div>
			</div>

		</div>
		<!--/col-3-->
		<div class="col-sm-9">

			<ul class="nav nav-tabs" role="tablist">
				<li class="nav-item"><a class="nav-link" href="#home"
					role="tab" data-toggle="tab">Profile</a></li>
				<li class="nav-item"><a class="nav-link" href="#orders"
					role="tab" data-toggle="tab">My Orders</a></li>

			</ul>


			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<hr>

					<div class="form-group">

						<div class="col-xs-6">
							<label for="fullName"><h4>Full Name</h4></label> <input
								type="text" class="form-control" name="fullName" id="fullName"
								placeholder="Full Name" disabled
								title="enter your first name if any." value="${user.fullName}">
						</div>
					</div>
					<div class="form-group">

						<div class="col-xs-6">
							<label for="mobile"><h4>Phone</h4></label> <input type="text"
								class="form-control" name="mobile" id="mobile" disabled
								placeholder="enter phone"
								title="enter your phone number if any." value="${user.mobile}">
						</div>
					</div>


					<div class="form-group">
						<div class="col-xs-6">
							<label for="emailid"><h4>Email</h4></label> <input type="email"
								disabled class="form-control" name="emailid" id="emailid"
								placeholder="you@email.com" title="enter your email."
								value="${user.emailid}">
						</div>
					</div>


					<div class="form-group">
						<div class="col-xs-12">
							<br> <a href="profileedit"><button
									class="btn btn-lg btn-success">
									<i class="glyphicon glyphicon-ok-sign"></i> Edit
								</button></a> <a href="deleteAccount"><button class="btn btn-lg">
									<i class="glyphicon glyphicon-repeat"></i> Delete Your Account
								</button></a>
						</div>
					</div>


					<hr>

				</div>
				<!--/tab-pane-->
				<div class="tab-pane" id="orders">
					<hr>
					<div class="container">
						<div class="row">
							<div class="span5">
								<table class="table table-striped table-condensed">
									<thead>
										<tr>
											<th>OrderId</th>
											<th>Username</th>
											<th>Date registered</th>
											<th>Item Name</th>
											<th>Price</th>
											<th>Status</th>
											<th>Invoice</th>
										</tr>
									</thead>
									<tbody>
									 <c:forEach items="${orderddetail}" var="ordereddata">
										<tr>
											<td rowspan="">${ordereddata.orderid}</td>
											<td>${user.fullName}</td>
											<td>${ordereddata.ordereddate}</td>
											<td>${ordereddata.cartItemName }</td>
											<td>${ordereddata.cartPrice }</td>
											<td><span class="label label-success">Pending</span></td>
											<td><a href="ordertoken">click</a></td>
										</tr>
									</c:forEach> 
										
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--/tab-pane-->
		</div>
		<!--/tab-content-->

	</div>
	<!--/col-9-->
</div>
<!--/row-->


</body>
</html>