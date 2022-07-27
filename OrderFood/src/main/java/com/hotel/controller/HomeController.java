package com.hotel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.constants.JspConstants;
import com.hotel.constants.UrlMappingConstants;
import com.hotel.model.FoodItems;
import com.hotel.service.FoodItemService;

@Controller
public class HomeController {
		
	private static final Object HOME = "Home";

	private static final String VEG = "Veg";

	private static final String ORDERLIST = "orderlist";
	
	private static final String NONVEG = "Non-Veg";

	private static final Object TITLE_VEG = "Select Veg";
	
	private static final Object TITLE_NONVEG = "Select Non-Veg";
	
	private static final Object TITLE_NONVEG2 = "Select Non-Veg2";
	
	@Autowired
	private FoodItemService foodItemService;
	
	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute(JspConstants.TITLE, HOME);
		return UrlMappingConstants.HOTEL_MERCHANT_USER_HOME;
	}
	
	@GetMapping(value=UrlMappingConstants.HOTEL_MERCHANT_VEG)
	public ModelAndView selectVeg(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_ORDER);

		String veg = VEG;
		List<FoodItems> list = foodItemService.getVeg(veg);
		modelAndView.addObject(ORDERLIST, list);
		modelAndView.addObject(JspConstants.TITLE, TITLE_VEG);
		return modelAndView;
	}

	@RequestMapping(value = UrlMappingConstants.HOTEL_MERCHANT_NONVEG)
	public ModelAndView selectNonveg() {
		ModelAndView modelAndView = new ModelAndView(UrlMappingConstants.HOTEL_MERCHANT_ORDER);
		String nonveg = NONVEG;
		List<FoodItems> list = this.foodItemService.getNonVeg(nonveg);
		modelAndView.addObject(ORDERLIST, list);
		modelAndView.addObject(JspConstants.TITLE, TITLE_NONVEG);
		return modelAndView;
	}
	
	
	
}
