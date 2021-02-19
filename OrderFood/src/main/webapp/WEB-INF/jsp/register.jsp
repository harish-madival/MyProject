<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="base.jsp"%>
<meta charset="UTF-8">
<style type="text/css">

/* The message box is shown when the user clicks on the password field */
#message {
	display: none;
	background: #f1f1f1;
	color: #000;
	position: relative;
	padding: 20px;
	margin-top: 10px;
}

#message p {
	padding: 10px 35px;
	font-size: 18px;
}

/* Add a green text color and a checkmark when the requirements are right */
.valid {
	color: green;
}

.valid:before {
	position: relative;
	left: -35px;
	content: "✔";
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
	color: red;
}

.invalid:before {
	position: relative;
	left: -35px;
	content: "✖";
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
		<div class="bg-danger">${deletestatus}</div>
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
					<div role="alert">
						<form:errors path="user.fullName" class="error" />
					</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-user"></i>
							</span>
						</div>
						<input name="fullName" class="form-control"
							placeholder="Full name" type="text">

					</div>

					<div role="alert">
						<form:errors path="user.emailId" class="error" />
						<div class="error">${emerror}</div>
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-envelope"></i>
							</span>
						</div>
						<input name="emailId" class="form-control"
							placeholder="Email address" type="email">
							
					</div>
					<p>* Provide active email address</p>

					<!-- form-group// -->
					<div role="alert">
						<form:errors path="user.mobile" class="error" />
						<div class="error">${mberror}</div>
					</div>
					<div id="demo"></div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-phone"></i>
							</span>
						</div>
						<select class="custom-select" style="max-width: 120px;">
							<option selected="">+91</option>
						</select> <input name="mobile" id="mobile" class="form-control"
							placeholder="Phone number" type="text" ">
							
					</div>
					<p>* Provide active Mobile number</p>
					

					<!-- form-group// -->
					<div role="alert">
						<form:errors path="user.userName" class="error" />
						<div class="error">${unerror}</div>
					</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<input name="userName" class="form-control"
							placeholder="Create Username" type="text">
					</div>

					<!-- form-group end.// -->
					<div role="alert">
						<form:errors path="user.userPassword" class="error" />
					</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<input name="userPassword" id="userPassword" class="form-control"
							placeholder="Create password" type="password"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Must contain at least one number and one uppercase 
							and lowercase letter, and at least 8 or more characters">
					</div>
					<div id="message">
						<h3>Password must contain the following:</h3>
						<p id="letter" class="invalid">
							A <b>lowercase</b> letter
						</p>
						<p id="capital" class="invalid">
							A <b>capital (uppercase)</b> letter
						</p>
						<p id="number" class="invalid">
							A <b>number</b>
						</p>
						<p id="length" class="invalid">
							Minimum <b>8 characters</b>
						</p>
					</div>
					<!-- form-group// -->
					<div role="alert">
						<form:errors path="user.confirmPassword" class="error" />
						<div class="error">${passworderror }</div>
					</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<input name="confirmPassword" class="form-control"
							placeholder="Repeat password" type="password">

					</div>
					<!-- form-group// -->
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-block" onclick="return validMobileNumber()">
							Create Account</button>
					</div>
					<!-- form-group// -->
					<p class="text-center">
						Have an account? <a href="login" >Log In</a>
					</p>

				</form>
			</article>
		</div>
		<!-- card.// -->

	</div>
	<!--container end.//-->
	<script>
		var myInput = document.getElementById("userPassword");
		var letter = document.getElementById("letter");
		var capital = document.getElementById("capital");
		var number = document.getElementById("number");
		var length = document.getElementById("length");

		// When the user clicks on the password field, show the message box
		myInput.onfocus = function() {
			document.getElementById("message").style.display = "block";
		}

		// When the user clicks outside of the password field, hide the message box
		myInput.onblur = function() {
			document.getElementById("message").style.display = "none";
		}

		// When the user starts to type something inside the password field
		myInput.onkeyup = function() {
			// Validate lowercase letters
			var lowerCaseLetters = /[a-z]/g;
			if (myInput.value.match(lowerCaseLetters)) {
				letter.classList.remove("invalid");
				letter.classList.add("valid");
			} else {
				letter.classList.remove("valid");
				letter.classList.add("invalid");
			}

			// Validate capital letters
			var upperCaseLetters = /[A-Z]/g;
			if (myInput.value.match(upperCaseLetters)) {
				capital.classList.remove("invalid");
				capital.classList.add("valid");
			} else {
				capital.classList.remove("valid");
				capital.classList.add("invalid");
			}

			// Validate numbers
			var numbers = /[0-9]/g;
			if (myInput.value.match(numbers)) {
				number.classList.remove("invalid");
				number.classList.add("valid");
			} else {
				number.classList.remove("valid");
				number.classList.add("invalid");
			}

			// Validate length
			if (myInput.value.length >= 8) {
				length.classList.remove("invalid");
				length.classList.add("valid");
			} else {
				length.classList.remove("valid");
				length.classList.add("invalid");
			}
		}
		
		/* mobile number validation */
		function validMobileNumber() {
  var x, text;

  // Get the value of the input field with id="numb"
  x = document.getElementById("mobile").value;

  // If x is Not a Number or less than one or greater than 10
  if (isNaN(x) || x<1 || x>9999999999) {
    text = "Not a valid mobile number";
    document.getElementById("demo").innerHTML = text;
    return false;
  } else {
    text = "Input OK";
    return true;
  }
  
}
	</script>
</body>
</html>