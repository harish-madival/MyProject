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
				<!--left col-->


				<div class="text-center">
					<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"
						class="avatar img-circle img-thumbnail" alt="avatar">
					<h6>Upload a different photo...</h6>
					<input type="file" class="text-center center-block file-upload">
				</div>
				</hr>
				<br>





				<ul class="list-group">
					<li class="list-group-item text-muted">Activity <i
						class="fas fa-tachometer-alt-slow"></i>
					<li class="list-group-item text-right"><span class="pull-left"><strong>Shares</strong></span>
						125</li>
					<li class="list-group-item text-right"><span class="pull-left"><strong>Likes</strong></span>
						13</li>
					<li class="list-group-item text-right"><span class="pull-left"><strong>Posts</strong></span>
						37</li>
					<li class="list-group-item text-right"><span class="pull-left"><strong>Followers</strong></span>
						78</li>
				</ul>

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
					<li class="nav-item active"><a class="nav-link active"
						href="#home" role="tab" data-toggle="tab">Profile</a></li>
					<li class="nav-item"><a class="nav-link" href="#orders"
						role="tab" data-toggle="tab">My Orders</a></li>

				</ul>


				<div class="tab-content">
					<div class="tab-pane active" id="home">
						<hr>
						<form class="form" action="##" method="post" id="registrationForm">
							<div class="form-group">

								<div class="col-xs-6">
									<label for="fullName"><h4>Full Name</h4></label> <input
										type="text" class="form-control" name="fullName"
										id="fullName" placeholder="Full Name"
										title="enter your first name if any." value="${user.fullName}">
								</div>
							</div>
							<div class="form-group">

								<div class="col-xs-6">
									<label for="mobile"><h4>Phone</h4></label> <input type="text"
										class="form-control" name="mobile" id="mobile"
										placeholder="enter phone"
										title="enter your phone number if any." value="${user.mobile}">
								</div>
							</div>

							
							<div class="form-group">
								<div class="col-xs-6">
									<label for="emailid"><h4>Email</h4></label> <input type="email"
										class="form-control" name="emailid" id="emailid"
										placeholder="you@email.com" title="enter your email." value="${user.emailid}">
								</div>
							</div>
							
							<div class="form-group">

								<div class="col-xs-6">
									<label for="userpassword"><h4>Password</h4></label> <input
										type="password" class="form-control" name="userpassword"
										id="userpassword" placeholder="password"
										title="enter your password." value="${user.userpassword}">
								</div>
							</div>
							<div class="form-group">

								<div class="col-xs-6">
									<label for="confirmpassword"><h4>Repeat Password</h4></label> <input
										type="password" class="form-control" name="confirmpassword"
										id="confirmpassword" placeholder="Repeat Password"
										title="enter your password2.">
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-12">
									<br>
									<button class="btn btn-lg btn-success" type="submit">
										<i class="glyphicon glyphicon-ok-sign"></i> Save
									</button>
									<button class="btn btn-lg" type="reset">
										<i class="glyphicon glyphicon-repeat"></i> Reset
									</button>
								</div>
							</div>
						</form>

						<hr>

					</div>
					<!--/tab-pane-->
					<div class="tab-pane" id="orders">
						<hr>
					</div>
				</div>
				<!--/tab-pane-->
			</div>
			<!--/tab-content-->

		</div>
		<!--/col-9-->
	</div>
	<!--/row-->
	<script type="text/javascript">
		$(document).ready(function() {

			var readURL = function(input) {
				if (input.files && input.files[0]) {
					var reader = new FileReader();

					reader.onload = function(e) {
						$('.avatar').attr('src', e.target.result);
					}

					reader.readAsDataURL(input.files[0]);
				}
			}

			$(".file-upload").on('change', function() {
				readURL(this);
			});
		});
	</script>

</body>
</html>