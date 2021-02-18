package com.hoteladmin.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hoteladmin.constants.JspConstants;
import com.hoteladmin.constants.UrlMapping;
import com.hoteladmin.model.FoodItems;
import com.hoteladmin.model.OrderDetail;
import com.hoteladmin.service.OrderService;
import com.hoteladmin.service.UserService;

@Controller
public class AdminController {
	
	private static final String NOTIFICATION = "ordereddata";

	private static final Object ADDFOOD = "Add Food";

	private static final Object HOME = "Home";

	@Autowired
	private UserService us;

	@Autowired
	private OrderService orderService;

	@RequestMapping("/")
	public String home(Model m) {
		m.addAttribute(JspConstants.TITLE, HOME);
		return UrlMapping.HOTEL_ADMIN_HOME;
	}

	@GetMapping(value = UrlMapping.HOTEL_ADMIN_ADDFOOD)
	public String addFood(Model m) {
		m.addAttribute(JspConstants.TITLE,ADDFOOD);
		return UrlMapping.HOTEL_FOOD_DETAIL;
	}

	@PostMapping(value = UrlMapping.HOTEL_ADMIN_ADDFOOD)
	public String addFood(@ModelAttribute FoodItems food, Model m) {
		this.us.saveFood(food);
		m.addAttribute(JspConstants.TITLE, ADDFOOD);
		return UrlMapping.HOTEL_FOOD_DETAIL;
	}

	

	@GetMapping(value= UrlMapping.HOTEL_FOOD_NOTIFICATION)
	public ModelAndView orderDetail(Model m) {
		ModelAndView mv = new ModelAndView(UrlMapping.HOTEL_FOOD_NOTIFICATION);
		List<OrderDetail> orderedData = orderService.getOrderedData();

		Comparator<OrderDetail> byOrderedDate = new Comparator<OrderDetail>() {
			public int compare(OrderDetail c1, OrderDetail c2) {
				return Long.valueOf(c1.getOrdereddate().getTime()).compareTo(c2.getOrdereddate().getTime());
			}
		};

		Collections.sort(orderedData, byOrderedDate.reversed());
		mv.addObject(NOTIFICATION, orderedData);
		m.addAttribute(JspConstants.TITLE, NOTIFICATION);
		return mv;
	}
}
