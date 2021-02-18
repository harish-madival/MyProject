package com.hotel.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmOneToManyCollectionElementType;
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

	private static final String LOGIN_STATUS = "loginfailed";

	private static final String LOGIN_STATUS_MESSAGE = "Username or password mismatch";

	private static final String LOGIN_TITLE = "Login";

	private static final String USERINFO = "user";

	private static final String ORDERDETAIL = "orderddetail";

	private static final String PROFILE_TITLE = "Profile";

	private static final String REGISTRATION_TITLE = "Register";

	private static final String REGISTRATION_STATUS = "registrationStatus";

	private static final String REGISTRATION_STATUS_MESSAGE = "Registered Successfully";

	private static final String VALIDATION_USERNAME_ERROR = "unerror";

	private static final String VALIDATION_USERNAME_ERROR_MESSAGE = "User Name already used";

	private static final String VALIDATION_MOBILE_ERROR = "mberror";

	private static final String VALIDATION_MOBILE_ERROR_MESSAGE = "Mobile Number already used";

	private static final String VALIDATION_EMAIL_ID_ERROR = "emerror";

	private static final String VALIDATION_EMAIL_ERROR_MESSAGE = "Email Id already used";

	private static final String TITLE_EDIT = "EditProfile";

	private static final String DELETE_STATUS = "deletestatus";

	private static final String DELETE_STATUS_MESSAGE = "Account deleted successfully";

	private static final String PASSWORD = "checkpassword";

	private static final String MATCH_PASSWORD = "passwordmismatch";

	private static final Object MATCH_PASSWORD_STATUS = "You entered wrong password";

	private static final String PROFILE_PAGE = "/WEB-INF/jsp/profile.jsp";

	private static final String EMAILID = "emailId";

	private static final String USER_PASSWORD = "userPassword";

	private static final String USERNAME = "userName";

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@GetMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN)
	public ModelAndView loginPage(HttpSession session, HttpServletRequest request) {
		ModelAndView mdv = new ModelAndView();

		if (session.getAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID) != null) {
			int uid = (Integer) session.getAttribute(UrlMappingConstants.SESSION_USER_ID);

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
	public ModelAndView login(@RequestParam(USERNAME) String userName, @RequestParam(USER_PASSWORD) String userPassword,
			HttpSession session, Model m) {
		ModelAndView mv = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_USER_HOME);

		try {
			User u = userService.validateUser(userName, userPassword);
			session.setAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID, userName);
			session.setAttribute(UrlMappingConstants.SESSION_USER_ID, u.getUserId());
			mv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_HOME);
		} catch (NullPointerException e) {
			mv.addObject(LOGIN_STATUS, LOGIN_STATUS_MESSAGE);
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
	public ModelAndView userRegistration(@Valid @ModelAttribute(USERINFO) User user, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_USER_REGISTRATION);
		if (result.hasErrors()) {
			modelAndView.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_REGISTRATION);
			return modelAndView;
		}

		try {
			if (user.getUserPassword().equals(user.getConfirmPassword())) {
				userService.createUser(user);
				modelAndView.addObject(REGISTRATION_STATUS, REGISTRATION_STATUS_MESSAGE);
			} else {
				modelAndView.addObject("passworderror", "password mismatch");
			}

		} catch (Exception e) {
			List<User> udata = this.userService.getAllUserData();
			for (User userdata : udata) {

				if (userdata.getUserName().equals(user.getUserName())) {
					modelAndView.addObject(VALIDATION_USERNAME_ERROR, VALIDATION_USERNAME_ERROR_MESSAGE);
				}
			}
			for (User userdata : udata) {
				if (userdata.getMobile().equals(user.getMobile())) {
					modelAndView.addObject(VALIDATION_MOBILE_ERROR, VALIDATION_MOBILE_ERROR_MESSAGE);
				}
			}
			for (User userdata : udata) {
				if (userdata.getEmailId().equals(user.getEmailId())) {
					modelAndView.addObject(VALIDATION_EMAIL_ID_ERROR, VALIDATION_EMAIL_ERROR_MESSAGE);
				}
			}
		}

		modelAndView.addObject(JspConstants.TITLE, REGISTRATION_TITLE);
		return modelAndView;
	}

	@GetMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE_EDIT)
	public ModelAndView editProfile(HttpSession session) {

		ModelAndView mdv = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE_EDIT);

		if (session.getAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID) != null) {
			int uid = (Integer) session.getAttribute(UrlMappingConstants.SESSION_USER_ID);

			User user = this.userService.getUserData(uid);
			mdv.addObject(USERINFO, user);

			mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE_EDIT);
			mdv.addObject(JspConstants.TITLE, TITLE_EDIT);
			return mdv;

		} else {
			mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN);
			mdv.addObject(JspConstants.TITLE, LOGIN_TITLE);
		}

		return mdv;
	}

	@PostMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE_UPDATE)
	public ModelAndView updateProfile(HttpSession session, @Valid @ModelAttribute(USERINFO) User user,
			BindingResult result) {
		ModelAndView mdv = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE);

		if (session.getAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID) != null) {
			int uid = (Integer) session.getAttribute(UrlMappingConstants.SESSION_USER_ID);
			User userdata = this.userService.getUserData(uid);
			userdata.setFullName(user.getFullName());
			userdata.setUserName(user.getUserName());
			userdata.setMobile(user.getMobile());
			userdata.setEmailId(user.getEmailId());

			User updatedata = this.userService.updateData(userdata);
			mdv.addObject(USERINFO, updatedata);
			mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE);

		} else {
			mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN);
			mdv.addObject(JspConstants.TITLE, LOGIN_TITLE);
		}

		return mdv;
	}

	@GetMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_DELETE_ACCOUNT)
	public ModelAndView deleteAccount(HttpSession session) {

		ModelAndView mdv = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_USER_REGISTRATION);

		if (session.getAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID) != null) {
			int uid = (Integer) session.getAttribute(UrlMappingConstants.SESSION_USER_ID);

			this.userService.deleteUser(uid);
			session.removeAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID);
			session.removeAttribute(UrlMappingConstants.SESSION_USER_ID);
			session.invalidate();
			mdv.addObject(DELETE_STATUS, DELETE_STATUS_MESSAGE);
			mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_REGISTRATION);
			mdv.addObject(JspConstants.TITLE, REGISTRATION_TITLE);
		} else {
			mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN);
			mdv.addObject(JspConstants.TITLE, LOGIN_TITLE);
		}

		return mdv;
	}

	@PostMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_VALIDATE_PASSWORD_FOR_EDIT)
	public ModelAndView validatePasswordForEditProfile(HttpSession session, @RequestParam(PASSWORD) String checkpass) {

		ModelAndView mdv = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE);
		if (session.getAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID) != null) {
			int uid = (Integer) session.getAttribute(UrlMappingConstants.SESSION_USER_ID);

			User user = this.userService.getUserData(uid);
			if (user.getUserPassword().equals(checkpass)) {
				mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE_EDIT);
				mdv.addObject(USERINFO, user);
			} else {
				mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE);
				mdv.addObject(MATCH_PASSWORD, MATCH_PASSWORD_STATUS);
				mdv.addObject(USERINFO, user);
			}
		} else {
			mdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN);
			mdv.addObject(JspConstants.TITLE, LOGIN_TITLE);
		}

		return mdv;
	}

	@PostMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_VALIDATE_PASSWORD_FOR_DELETE)
	public ModelAndView validatePasswordForDeleteAccount(HttpSession session, @RequestParam(PASSWORD) String checkpass,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ModelAndView rdv = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE);
		RequestDispatcher rd;
		if (session.getAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID) != null) {
			int uid = (Integer) session.getAttribute(UrlMappingConstants.SESSION_USER_ID);

			User user = this.userService.getUserData(uid);
			if (user.getUserPassword().equals(checkpass)) {

				response.sendRedirect(UrlMappingConstants.HOTEL_MERCHANT_USER_DELETE_ACCOUNT);
			} else {
				request.setAttribute(MATCH_PASSWORD, MATCH_PASSWORD_STATUS);
				rd = request.getRequestDispatcher(PROFILE_PAGE);
				rd.forward(request, response);
			}
		} else {
			rdv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_PROFILE);
		}

		return rdv;
	}

	@GetMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_LOGOUT)
	public String logOut(HttpSession session) {

		session.removeAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID);
		session.removeAttribute(UrlMappingConstants.SESSION_USER_ID);
		session.invalidate();
		return UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN;
	}
}
