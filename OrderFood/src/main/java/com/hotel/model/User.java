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
	@Size(min=6,message="required")
	@Column(unique = true)
	private String username;
	@NotEmpty(message = "Field should not be Empty")
	@Column(unique = false)
	private String userpassword;
	@NotEmpty(message = "Field should not be Empty")
	@Column(unique = false)
	private String confirmpassword;
	@Column(unique = true)
	@Size(min = 10,max = 10,message = "Provide proper mobile number")
	private String mobile;
	@Column(unique = true)
	@NotEmpty(message = "Field should not be Empty") @Email
	private String emailid;
	@NotNull
	private String gender;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Cart> cart;
		
	
	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", userpassword=" + userpassword
				+ ", confirmpassword=" + confirmpassword + ", mobile=" + mobile + ", emailid=" + emailid + ", gender="
				+ gender + ", cart=" + cart + "]";
	}
	
}
