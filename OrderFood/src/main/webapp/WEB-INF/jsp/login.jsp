<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="base.jsp"%>
<meta charset="UTF-8">

</head>
<body>
	<header>
		<%@include file="nav.jsp"%>
	</header>
	<div class="container mt-5">
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
	</div>
</body>
</html>