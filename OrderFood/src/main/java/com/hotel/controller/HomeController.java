package com.hotel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.model.FoodItems;
import com.hotel.service.UserService;

@Controller
public class HomeController {
	
	
	@RequestMapping("/")
	public String home(Model m) {
		m.addAttribute("title", "Home");
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logOut(HttpSession session) {
		
		session.removeAttribute("email");
		session.removeAttribute("userid");
		session.invalidate();
		return "login";
	}
	
	
	
}
