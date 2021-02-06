<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="base.jsp"%>
<meta charset="UTF-8">
<style type="text/css">
.error {
	background-color: red;
	color: white;
}

.divider-text {
	position: relative;
	text-align: center;
	margin-top: 15px;
	margin-bottom: 15px;
}

.divider-text span {
	padding: 7px;
	font-size: 12px;
	position: relative;
	z-index: 2;
}

.divider-text:after {
	content: "";
	position: absolute;
	width: 100%;
	border-bottom: 1px solid #ddd;
	top: 55%;
	left: 0;
	z-index: 1;
}

.btn-facebook {
	background-color: #405D9D;
	color: #fff;
}

.btn-twitter {
	background-color: #42AEEC;
	color: #fff;
}
</style>
</head>
<body>
	<header>
		<%@include file="nav.jsp"%>
	</header>
	<div class="container">
		<br>
		<div class="bg-success">${registrationStatus}</div>
		<div class="bg-danger"> ${deletestatus}</div>
		<div class="card bg-light">
			<article class="card-body mx-auto" style="max-width: 400px;">
				<h4 class="card-title mt-3 text-center">Create Account</h4>
				<p class="text-center">Get started with your free account</p>
				<p>
					<a href="" class="btn btn-block btn-twitter"> <i
						class="fab fa-twitter"></i>   Login via Twitter
					</a> <a href="" class="btn btn-block btn-facebook"> <i
						class="fab fa-facebook-f"></i>   Login via facebook
					</a>
				</p>
				<p class="divider-text">
					<span class="bg-light">OR</span>
				</p>
				<form action="register" method="post">
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-user"></i>
							</span>
						</div>
						<input name="fullName" class="form-control"
							placeholder="Full name" type="text">
						<div role="alert">
							<form:errors path="u.fullName" class="error" />
						</div>
					</div>

					<div role="alert">
						<form:errors path="u.emailid" class="error" />
						<div class="error">${emerror}</div>
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-envelope"></i>
							</span>
						</div>
						<input name="emailid" class="form-control"
							placeholder="Email address" type="email">
					</div>

					<!-- form-group// -->
					<div role="alert">
						<form:errors path="u.mobile" class="error" />
						<div class="error">${mberror}</div>
					</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-phone"></i>
							</span>
						</div>
						<select class="custom-select" style="max-width: 120px;">
							<option selected="">+91</option>
						</select> <input name="mobile" class="form-control"
							placeholder="Phone number" type="text">
					</div>

					<!-- form-group// -->
					<div role="alert">
						<form:errors path="u.username" class="error" />
						<div class="error">${unerror}</div>
					</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<input name="username" class="form-control"
							placeholder="Create Username" type="text">
					</div>

					<!-- form-group end.// -->
					<div role="alert">
							<form:errors path="u.userpassword" class="error" />
						</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<input name="userpassword" class="form-control"
							placeholder="Create password" type="password">
					</div>
					<!-- form-group// -->
					<div role="alert">
							<form:errors path="u.confirmpassword" class="error" />
							<div class="error">${passworderror }</div>
						</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<input name="confirmpassword" class="form-control"
							placeholder="Repeat password" type="password">
						
					</div>
					<!-- form-group// -->
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-block">
							Create Account</button>
					</div>
					<!-- form-group// -->
					<p class="text-center">
						Have an account? <a href="login">Log In</a>
					</p>
					
				</form>
			</article>
		</div>
		<!-- card.// -->

	</div>
	<!--container end.//-->

</body>
</html>