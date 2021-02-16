package com.hotel.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.hotel.constants.JspConstants;
import com.hotel.constants.UrlMappingConstants;
import com.hotel.model.OrderDetail;
import com.hotel.model.User;
import com.hotel.service.OrderService;
import com.hotel.service.UserService;

@Controller
public class LoginController {
	
	private static final String SESSION_USER_EMAIL_ID = "email";

	private static final String SESSION_USER_ID = "userid";

	private static final String LOGIN_STATUS = "loginfailed";

	private static final Object LOGIN_STATUS_MESSAGE = "Username or password mismatch";

	private static final Object LOGIN_TITLE = "Login";

	private static final String USERINFO = "user";

	private static final String ORDERDETAIL = "orderddetail";

	private static final Object PROFILE_TITLE = "Profile";

	private static final Object REGISTRATION_TITLE = "Register";

	private static final String REGISTRATION_STATUS = "registrationStatus";

	private static final Object REGISTRATION_STATUS_MESSAGE = "Registered Successfully";

	private static final String VALIDATION_USERNAME_ERROR = "unerror";

	private static final Object VALIDATION_USERNAME_ERROR_MESSAGE = "User Name already used";

	private static final String VALIDATION_MOBILE_ERROR = "mberror";

	private static final Object VALIDATION_MOBILE_ERROR_MESSAGE = "Mobile Number already used";

	private static final String VALIDATION_EMAIL_ID_ERROR = "emerror";

	private static final Object VALIDATION_EMAIL_ERROR_MESSAGE = "Email Id already used";

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@GetMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN)
	public ModelAndView loginPage(HttpSession session,HttpServletRequest request) {
		ModelAndView mdv = new ModelAndView();

		if (session.getAttribute(SESSION_USER_EMAIL_ID) != null) {
			int uid = (Integer) session.getAttribute(SESSION_USER_ID);

			User user = this.userService.getUserData(uid);
			List<OrderDetail> orderddetail = this.orderService.geOrderedData(uid);
			mdv.addObject(USERINFO, user);
			mdv.addObject(ORDERDETAIL, orderddetail);
			mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE);
			mdv.addObject(JspConstants.TITLE, PROFILE_TITLE);
		} else {
			mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN);
			mdv.addObject(JspConstants.TITLE, LOGIN_TITLE);
		}

		return mdv;
	}

	@PostMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN)
	public ModelAndView login(@RequestParam String emailid, @RequestParam String userpassword, HttpSession session,
			Model m) {
		ModelAndView mv = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_USER_HOME);

		User u = userService.validateUser(emailid, userpassword);
		int uid = u.getUserId();
		
		if (u.getEmailid().equals(emailid)){
			session.setAttribute(SESSION_USER_EMAIL_ID, emailid);
			session.setAttribute(SESSION_USER_ID, uid);
			mv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_HOME);
		} else {
			m.addAttribute(LOGIN_STATUS, LOGIN_STATUS_MESSAGE);
			mv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN);
		}
		m.addAttribute(JspConstants.TITLE, LOGIN_TITLE);
		return mv;
	}

	@GetMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_REGISTRATION)
	public ModelAndView userRegistrationPage() {

		ModelAndView modelAndView = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_USER_REGISTRATION);
		modelAndView.addObject(JspConstants.TITLE, REGISTRATION_TITLE);
		return modelAndView;
	}

	@PostMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_REGISTRATION)
	public ModelAndView userRegistration(@Valid @ModelAttribute("u") User u, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_USER_REGISTRATION);
		if (result.hasErrors()) {
			modelAndView.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_REGISTRATION);
			return modelAndView;
		}

		try {
			if (u.getUserpassword().equals(u.getConfirmpassword())) {
				userService.createUser(u);				
				modelAndView.addObject(REGISTRATION_STATUS,REGISTRATION_STATUS_MESSAGE);
			} else {
				modelAndView.addObject("passworderror", "password mismatch");
			}

		} catch (Exception e) {
			List<User> udata = this.userService.getAllUserData();
			for (User user : udata) {

				if (user.getUsername().equals(u.getUsername())) {
					modelAndView.addObject(VALIDATION_USERNAME_ERROR, VALIDATION_USERNAME_ERROR_MESSAGE);
				}
			}
			for (User user : udata) {
				if (user.getMobile().equals(u.getMobile())) {
					modelAndView.addObject(VALIDATION_MOBILE_ERROR, VALIDATION_MOBILE_ERROR_MESSAGE);
				}
			}
			for (User user : udata) {
				if (user.getEmailid().equals(u.getEmailid())) {
					modelAndView.addObject(VALIDATION_EMAIL_ID_ERROR, VALIDATION_EMAIL_ERROR_MESSAGE);
				}
			}
		}

		modelAndView.addObject(JspConstants.TITLE, REGISTRATION_TITLE);
		return modelAndView;
	}

	@RequestMapping(value = "/profileedit", method = RequestMethod.GET)
	public ModelAndView editProfile(HttpSession session) {

		ModelAndView mdv = new ModelAndView("profileedit");

		if (session.getAttribute("email") != null) {
			int uid = (Integer) session.getAttribute("userid");

			User user = this.userService.getUserData(uid);
			mdv.addObject("user", user);

			mdv.setViewName("profileedit");
			mdv.addObject("title", "EditProfile");
			return mdv;

		} else {
			mdv.setViewName("login");
			mdv.addObject("title", "Login");
		}

		return mdv;
	}

	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
	public ModelAndView updateProfile(HttpSession session, @Valid @ModelAttribute("u") User u, BindingResult result) {

		ModelAndView mdv = new ModelAndView("profile");

		if (session.getAttribute("email") != null) {
			int uid = (Integer) session.getAttribute("userid");

			if (result.hasErrors()) {
				mdv.setViewName("profileedit");
				return mdv;
			}

			User user = this.userService.getUserData(uid);
			user.setFullName(u.getFullName());
			user.setUsername(u.getUsername());
			user.setMobile(u.getMobile());
			user.setEmailid(u.getEmailid());

			User updatedata = this.userService.updateData(user);
			mdv.addObject("user", updatedata);
			mdv.setViewName("profile");
			session.removeAttribute("valid");

		} else {
			mdv.setViewName("login");
			mdv.addObject("title", "Login");
		}

		return mdv;
	}

	@RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
	public ModelAndView deleteAccount(HttpSession session) {

		ModelAndView mdv = new ModelAndView("register");

		if (session.getAttribute("email") != null) {
			int uid = (Integer) session.getAttribute("userid");

			this.userService.deleteUser(uid);
			session.removeAttribute("userid");
			session.invalidate();
			mdv.addObject("deletestatus", "Account deleted successfully");
			mdv.setViewName("register");
			mdv.addObject("title", "register");
		} else {
			mdv.setViewName("login");
			mdv.addObject("title", "Login");
		}

		return mdv;
	}

	@PostMapping("/validateforprofilepassword")
	public ModelAndView validatePasswordForEditProfile(HttpSession session, @RequestParam("checkpassword") String checkpass) {

		ModelAndView mdv = new ModelAndView("profile");
		System.out.println("password:" + checkpass);
		if (session.getAttribute("email") != null) {
			int uid = (Integer) session.getAttribute("userid");

			User user = this.userService.getUserData(uid);
			if (user.getUserpassword().equals(checkpass)) {
				mdv.setViewName("profileedit");
				mdv.addObject("user", user);				
			}else {
				mdv.setViewName("profile");
				mdv.addObject("passwordmismatch", "You entered wrong password");
				mdv.addObject("user", user);
			}
		} else {
			mdv.setViewName("login");
			mdv.addObject("title", "Login");
		}

		return mdv;
	}
	
	@PostMapping("/validatefordeleteacount")
	public ModelAndView validatePasswordForDeleteAccount(HttpSession session, 
			@RequestParam("checkpassword") String checkpass, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ModelAndView rdv = new ModelAndView("profile");
		RequestDispatcher rd;
		if (session.getAttribute("email") != null) {
			int uid = (Integer) session.getAttribute("userid");

			User user = this.userService.getUserData(uid);
			if (user.getUserpassword().equals(checkpass)) {
				/*
				 * rd=request.getRequestDispatcher("deleteAccount"); rd.forward(request,
				 * response);
				 */
				response.sendRedirect("deleteAccount");
			}else {
				request.setAttribute("passwordmismatched", "You entered wrong password");
				rd=request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
				rd.forward(request, response);
			}
		} else {
			rdv.setViewName("profile");
		}

		return rdv;
	}
	
	@RequestMapping("/logout")
	public String logOut(HttpSession session) {
		
		session.removeAttribute("email");
		session.removeAttribute("userid");
		session.invalidate();
		return "login";
	}
}
