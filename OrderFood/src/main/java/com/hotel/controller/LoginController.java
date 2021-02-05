package com.hotel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hotel.model.User;
import com.hotel.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpSession session) {
		ModelAndView mdv = new ModelAndView();

		if (session.getAttribute("email") != null) {
			int uid = (Integer) session.getAttribute("userid");

			User user = this.userService.getUserData(uid);
			System.out.println(user);
			mdv.addObject("user", user);
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
}
