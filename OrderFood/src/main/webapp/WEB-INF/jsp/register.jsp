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
</style>
</head>
<body>
	<header>
		<%@include file="nav.jsp"%>
	</header>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-8 offset-md-2">
				<div class="bg-success">${registrationStatus}</div>
				<div class="card">
					<div class="card-body">
						<h1 class="card-title">Register User</h1>
						<form action="register" method="post">
							<div class="form-group">
								<label for="username">User Name</label> <input
									class="form-control" type="text " id="username" name="username" />
									<div class="error">${unerror}</div>
								<div role="alert">
									<form:errors path="u.username" class="error" />

								</div>
							</div>
							<div class="form-group">
								<label for="userpassword">Password</label> <input
									class="form-control" type="password" id="userpassword"
									name="userpassword" />
								<div role="alert">
									<form:errors path="u.userpassword" class="error" />
								</div>
							</div>
							<div class="form-group">
								<label for="confirmpassword">Confirm-Password</label> <input
									class="form-control" type="password" id="confirmpassword"
									name="confirmpassword" />
								<div role="alert">
									<form:errors path="u.confirmpassword" class="error" />
									<div class="error">${passworderror }</div>
								</div>
							</div>
							<div class="form-group">
								<label for="mobile">Mobile</label> <input class="form-control"
									type="text" id="mobile" name="mobile" />
								<div role="alert">
									<form:errors path="u.mobile" class="error" />

									<div class="error">${mberror}</div>

								</div>
							</div>
							<div class="form-group">
								<label for="emailid">Email Id</label> <input
									class="form-control" type="email" id="emailid" name="emailid" />
								<div role="alert">
									<form:errors path="u.emailid" class="error" />

									<div class="error">${emerror}</div>

								</div>
							</div>
							<div class="card">
								<div class="card-body">
									<div class="form-group">
										<label>Gender</label>
										<div class="form-check">
											<input class="form-check-input" type="radio" name="gender"
												id="genderm" value="Male" checked> <label
												class="form-check-label" for="genderm"> Male </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" type="radio" name="gender"
												id="genderf" value="Female"> <label
												class="form-check-label" for="genderf"> Female </label>
										</div>
										<div role="alert">
											<form:errors path="u.gender" class="error" />

										</div>
									</div>
								</div>
							</div>
							<div class="form-group mt-2">
								<button type="submit" class="btn btn-success">Register</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>