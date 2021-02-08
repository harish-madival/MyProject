package com.hotel.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hotel.model.OrderDetail;
import com.hotel.model.User;
import com.hotel.service.OrderService;
import com.hotel.service.UserService;

import antlr.TreeParserSharedInputState;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpSession session) {
		ModelAndView mdv = new ModelAndView();

		if (session.getAttribute("email") != null) {
			int uid = (Integer) session.getAttribute("userid");

			User user = this.userService.getUserData(uid);
			List<OrderDetail> orderddetail=this.orderService.geOrderedData(uid);
			//Collections.sort(orderddetail, Collections.reverseOrder()); 
			
			mdv.addObject("user", user);
			mdv.addObject("orderddetail", orderddetail);
			mdv.setViewName("profile");
			mdv.addObject("title", "Profile");
		} else {
			mdv.setViewName("login");
			mdv.addObject("title", "Login");
		}

		return mdv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String emailid, 
			@RequestParam String userpassword,
			HttpSession session,Model m) {
		ModelAndView mv = new ModelAndView("index");

		List<User> u = userService.validateUser(emailid, userpassword);
		int uid = 0;
		for (User user : u) {
			uid = user.getUserId();
		}
		if (u.size() == 1) {
			session.setAttribute("email", emailid);
			session.setAttribute("userid", uid);
			mv.setViewName("index");
		} else {
			m.addAttribute("loginfailed", "Username or password mismatch");
			mv.setViewName("login");
		}
		m.addAttribute("title", "Login");
		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView userRegistrationPage() {

		ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("title", "Register");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView userRegistration(@Valid @ModelAttribute("u") User u, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("register");
		if (result.hasErrors()) {
			modelAndView.setViewName("register");
			return modelAndView;
		}

		try {
			modelAndView.addObject("user", u);
			if (u.getUserpassword().equals(u.getConfirmpassword())) {
				User userdata = this.userService.createUser(u);
				modelAndView.addObject("dta", userdata);
				modelAndView.addObject("registrationStatus", "Registered Successfully");
			} else {
				modelAndView.addObject("passworderror", "password mismatch");
			}

		} catch (Exception e) {
			List<User> udata = this.userService.getAllUserData();
			for (User user : udata) {

				if (user.getUsername().equals(u.getUsername())) {
					modelAndView.addObject("unerror", "User Name already used");
				}
			}
			for (User user : udata) {
				if (user.getMobile().equals(u.getMobile())) {
					modelAndView.addObject("mberror", "Mobile Number already used");
				}
			}
			for (User user : udata) {
				if (user.getEmailid().equals(u.getEmailid())) {
					modelAndView.addObject("emerror", "Email Id already used");
				}
			}
		}

		modelAndView.addObject("title", "Register");
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
		} else {
			mdv.setViewName("login");
			mdv.addObject("title", "Login");
		}
		
		return mdv;
	}
	
	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
	public ModelAndView updateProfile(HttpSession session,@Valid @ModelAttribute("u") User u, BindingResult result) {

		ModelAndView mdv = new ModelAndView("profile");
		
		if (session.getAttribute("email") != null) {
			int uid = (Integer) session.getAttribute("userid");
			
//			if (result.hasErrors()) {
//				mdv.setViewName("profileedit");
//				return mdv;
//			}
			
			User user = this.userService.getUserData(uid);
			user.setFullName(u.getFullName());
			user.setUsername(u.getUsername());
			user.setMobile(u.getMobile());
			user.setEmailid(u.getEmailid());
			
			User updatedata=this.userService.updateData(user);
			mdv.addObject("user", updatedata);
			mdv.setViewName("profile");
			
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
}
