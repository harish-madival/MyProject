package com.hoteladmin.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hoteladmin.model.FoodItems;
import com.hoteladmin.model.OrderDetail;
import com.hoteladmin.service.OrderService;
import com.hoteladmin.service.UserService;

@Controller
public class AdminController {
	@Autowired
	private UserService us;
	
	@Autowired
	private OrderService orderservice;
	
	@RequestMapping("/")
	public String home(Model m) {
		m.addAttribute("title", "Home");
		return "index";
	}

	@RequestMapping(value = "/addfood", method = RequestMethod.GET)
	public String addfood(Model m) {
		m.addAttribute("title", "addfood");
		return "fooditems";
	}

	@RequestMapping(value = "/addfood", method = RequestMethod.POST)
	public String addfood(@ModelAttribute FoodItems food, Model m) {
		this.us.saveFood(food);
		m.addAttribute("title", "addfood");
		return "fooditems";
	}
	
	@RequestMapping(value = "/notification", method = RequestMethod.GET)
	public String notification(Model m) {
		m.addAttribute("title", "notificatio");
		return "fooditems";
	}
	
	@RequestMapping("/ordered")
	public ModelAndView orderDetail(Model m) {
		ModelAndView mv=new ModelAndView("orderdetail");
		List<OrderDetail> ordereddata=this.orderservice.getOrderedData();
		
		Comparator<OrderDetail> byorderedadate = new Comparator<OrderDetail>() {
		      public int compare(OrderDetail c1, OrderDetail c2) {
		        return Long.valueOf(c1.getOrdereddate().getTime()).compareTo(c2.getOrdereddate().getTime());
		      }
		    };
		    
		        Collections.sort(ordereddata, byorderedadate.reversed());
		        
		    for(int i=0; i<ordereddata.size(); i++) {
		      System.out.println(ordereddata.get(i));  
		    }
		mv.addObject("ordereddata",ordereddata);
		System.out.println(ordereddata);
		m.addAttribute("title", "ordered");
		return mv;
	}
}
