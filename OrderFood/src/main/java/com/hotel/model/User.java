package com.hotel.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;



@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@NotEmpty(message = "Field should not be Empty")
	private String fullName;
	
	@Column(unique = true)
	@NotEmpty(message = "Field should not be Empty") @Email
	private String emailId;
	
	@Column(unique = true)
	@Size(min = 10,max = 10,message = "Provide proper mobile number")
	private String mobile;
	
	@Size(min=6,message="Usrname must be at least 6 character")	
	private String userName;
	
	
	@NotEmpty(message = "Field should not be Empty")	
	private String userPassword;
	
	@NotEmpty(message = "Field should not be Empty")
	private String confirmPassword;
	
	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	private List<Cart> cart;	
	
	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", emailid=" + emailId + ", mobile=" + mobile
				+ ", username=" + userName + ", userpassword=" + userPassword + ", confirmpassword=" + confirmPassword
				+ ", cart=" + cart + "]";
	}

	
}
