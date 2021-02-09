<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="base.jsp"%>
<meta charset="UTF-8">
<style type="text/css">
</style>
</head>
<body>
	<header>
		<%@include file="nav.jsp"%>
	</header>
	<%-- <div class="container mt-5">
		<div class="row">
			<div class="col-md-8 offset-md-2">
				<div class="card ">
					<div class="card-body ">
						<h1 class="card-title">
							Login
							<hr class="hr1" />
						</h1>

						<form action="login" method="post">
							<form>
								<div class="form-group">
									<label for="emailid">Email address</label> <input type="email"
										class="form-control" id="emailid" name="emailid"
										aria-describedby="emailHelp" placeholder="Enter email">
									<small id="emailHelp" class="form-text text-muted">We'll
										never share your email with anyone else.</small>
								</div>
								<div class="form-group">
									<label for="userpassword">Password</label> <input
										type="password" class="form-control" id="userpassword"
										name="userpassword" placeholder="Password">
								</div>

								<button type="submit" class="btn btn-primary">Submit</button>
								<div>${loginfailed}</div>
								<button class="btn btn-outline-primary">
									<a href="register">New User??</a>
								</button>
								
								<button class="btn btn-outline-primary">
									<a>Forgot Password</a>
								</button>
							</form>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div> --%>


	<div class="container">


		<div class="omb_login">
			<h3 class="omb_authTitle">
				Login or <a href="#">Sign up</a>
			</h3>
			<div class="row omb_row-sm-offset-3 omb_socialButtons">
				<div class="col-xs-4 col-sm-2">
					<a href="#" class="btn btn-lg btn-block omb_btn-facebook"> <i
						class="fa fa-facebook visible-xs"></i> <span class="hidden-xs">Facebook</span>
					</a>
				</div>
				<div class="col-xs-4 col-sm-2">
					<a href="#" class="btn btn-lg btn-block omb_btn-twitter"> <i
						class="fa fa-twitter visible-xs"></i> <span class="hidden-xs">Twitter</span>
					</a>
				</div>
				<div class="col-xs-4 col-sm-2">
					<a href="#" class="btn btn-lg btn-block omb_btn-google"> <i
						class="fa fa-google-plus visible-xs"></i> <span class="hidden-xs">Google+</span>
					</a>
				</div>
			</div>

			<div class="row omb_row-sm-offset-3 omb_loginOr">
				<div class="col-xs-12 col-sm-6">
					<hr class="omb_hrOr">
					<span class="omb_spanOr">or</span>
				</div>
			</div>

			<div class="row omb_row-sm-offset-3">
				<div class="col-xs-12 col-sm-6">
					<form class="omb_loginForm" action="login" method="post">
						<div class="input-group">
							<span class="input-group-addon mr-2"><i class="fa fa-user"></i></span>
							<input type="text" class="form-control" name="emailid"
								placeholder="email address">
						</div>
						<span class="help-block"></span>

						<div class="input-group mt-2">
							<span class="input-group-addon mr-2"><i class="fa fa-lock"></i></span>
							<input type="password" class="form-control" name="userpassword"
								placeholder="Password">
						</div>
						
						<span class="help-block">${loginfailed}</span>

						<button class="btn btn-lg btn-primary btn-block mt-2" type="submit">Login</button>
					</form>
				</div>
			</div>
			<div class="row omb_row-sm-offset-3">
				<div class="col-xs-12 col-sm-3">
					<p class="omb_forgotPwd">
						<a href="register">SignUp</a>
					</p>
				</div>
				
				<div class="col-xs-12 col-sm-3">
					<p class="omb_forgotPwd">
						<a href="#">Forgot password?</a>
					</p>
				</div>
			</div>
		</div>

	</div>
</body>
</html>