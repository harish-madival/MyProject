package com.hotel.constants;

public final class UrlMappingConstants {

	private UrlMappingConstants() {
		super();
	}
	
	public static final String SESSION_USER_EMAIL_ID = "email";

	public static final String SESSION_USER_ID = "userid";
	
	//=======Home Controller==============
	public static final String HOTEL_MERCHANT_USER_HOME = "index";
	
	public static final String HOTEL_MERCHANT_VEG = "selectveg";
	
	public static final String HOTEL_MERCHANT_NONVEG = "selectnonveg";
	
	public static final String HOTEL_MERCHANT_ORDER = "order";
	
	//===========Login controller==========
	public static final String HOTEL_MERCHANT_USER_LOGOUT="logout";
	
	public static final String HOTEL_MERCHANT_USER_LOGIN="login";
	
	public static final String HOTEL_MERCHANT_USER_PROFILE = "profile";
	
	public static final String HOTEL_MERCHANT_USER_REGISTRATION = "register";
	
	public static final String HOTEL_MERCHANT_USER_PROFILE_UPDATE = "updateprofile";
	
	public static final String HOTEL_MERCHANT_USER_PROFILE_EDIT = "profileedit";
	
	public static final String HOTEL_MERCHANT_USER_DELETE_ACCOUNT = "deleteAccount";
	
	public static final String HOTEL_MERCHANT_USER_VALIDATE_PASSWORD_FOR_EDIT = "validateforprofilepassword";
	
	public static final String HOTEL_MERCHANT_USER_VALIDATE_PASSWORD_FOR_DELETE = "validatefordeleteacount";
	
	//=========Order controller=========
	public static final String HOTEL_MERCHANT_USER_CART = "cart";
	
	public static final String HOTEL_MERCHANT_USER_CART_DELETE = "delete";

	public static final String HOTEL_MERCHANT_USER_CART_BUY = "buy";

	public static final String HOTEL_MERCHANT_USER_TOKEN = "ordertoken";
	
	
}
