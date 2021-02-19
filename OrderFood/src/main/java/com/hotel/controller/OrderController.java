package com.hotel.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hotel.constants.JspConstants;
import com.hotel.constants.UrlMappingConstants;
import com.hotel.model.Cart;
import com.hotel.model.FoodItems;
import com.hotel.model.OrderDetail;
import com.hotel.model.User;
import com.hotel.service.CartService;
import com.hotel.service.FoodItemService;
import com.hotel.service.OrderService;
import com.hotel.service.UserService;

@Controller
public class OrderController {

	private static final String SUBTOTAL = "subtotal";

	private static final String CART_DATA = "carteddata";

	private static final String CART_TITLE = "Cart";

	private static final String BUY_STATUS = "buystatus";

	private static final String BUY_STATUS_MESSAGE = "carted items not found";

	private static final String TITLE_BUY = "buy";

	private static final String ORDER_DETAIL = "orderDetail";

	private static final String TOTAL_AMOUNT = "totalamount";

	private static final Object LOGIN_TITLE = "LOGIN";

	private static final String PATH_ID = "id";

	@Autowired
	private FoodItemService foodItemService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;

	

	@RequestMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_CART)
	public String cartedItems(Model m, HttpServletRequest req, HttpSession session) {
		long subtotal=0;
		if (session.getAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID) != null) {
			int uid = (Integer) session.getAttribute(UrlMappingConstants.SESSION_USER_ID);
			List<Cart> carteddata = this.cartService.getAllCartedDataByUid(uid);
			for (Cart cart : carteddata) {
				subtotal=subtotal+cart.getTotalPrice();
			}
			m.addAttribute(SUBTOTAL, subtotal);
			m.addAttribute(CART_DATA, carteddata);
			m.addAttribute(JspConstants.TITLE, CART_TITLE);
		} else {
			return UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN;
		}

		return UrlMappingConstants.HOTEL_MERCHANT_USER_CART;
	}

	@RequestMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_CART+"/{"+PATH_ID+"}")
	public RedirectView cartedItem(@ModelAttribute("cart") Cart cart,@PathVariable(PATH_ID) int id, Model m, HttpServletRequest req, HttpSession session) {
		RedirectView rdview = new RedirectView();
		long totalprice;
		long subtotal=0;
		if (session.getAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID) != null) {
			FoodItems food = this.foodItemService.getdata(id);
			int uid = (Integer) session.getAttribute("userid");
			User user=userService.getUserData(uid);
			Random rand = new Random();
			int cid = rand.nextInt(1000);			
			cart.setId(cid);
			cart.setItemName(food.getItemName());
			cart.setPrice(food.getPrice());
			//cart.setUserId(uid);
			cart.setUser(user);
			totalprice=cart.getPrice() * cart.getQuantity();
			cart.setTotalPrice(totalprice);
			subtotal=subtotal+totalprice;
			m.addAttribute(SUBTOTAL, subtotal);
			m.addAttribute(JspConstants.TITLE, CART_TITLE);			
			this.cartService.addtocart(cart);
			rdview.setUrl(req.getContextPath() + "/"+UrlMappingConstants.HOTEL_MERCHANT_USER_CART);

		} else {
			rdview.setUrl(req.getContextPath() + "/"+UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN);
		}
		return rdview;

	}

	@RequestMapping(UrlMappingConstants.HOTEL_MERCHANT_USER_CART_DELETE+"/{"+PATH_ID+"}")
	public RedirectView delete(@PathVariable(PATH_ID) int id, HttpServletRequest req, Model m) {
		RedirectView rdview = new RedirectView();
		m.addAttribute(JspConstants.TITLE, UrlMappingConstants.HOTEL_MERCHANT_USER_CART);
		this.cartService.delete(id);
		rdview.setUrl(req.getContextPath() + "/"+UrlMappingConstants.HOTEL_MERCHANT_USER_CART);
		return rdview;

	}

	@RequestMapping(value = UrlMappingConstants.HOTEL_MERCHANT_USER_CART_BUY)
	public ModelAndView buy(HttpServletRequest req, Model m, HttpSession session) {
		ModelAndView rv = new ModelAndView();
		if (session.getAttribute(UrlMappingConstants.SESSION_USER_EMAIL_ID) != null) {
			int uid = (Integer) session.getAttribute(UrlMappingConstants.SESSION_USER_ID);
			List<Cart> carteddata = this.cartService.getAllCartedDataByUid(uid);
			OrderDetail od = new OrderDetail();
			Random rand = new Random();
			long oid = rand.nextInt(1000);
			OrderDetail od1;
			Date date=new Date();
			od.setOrderId(oid);
			for (Cart cart : carteddata) {
				int id=rand.nextInt(1000);
				od.setOrderedDate(date);
				od.setId(id);
				od.setCartId(cart.getId());
				od.setCartItemName(cart.getItemName());
				od.setCartPrice(cart.getTotalPrice());
				od.setUserId(uid);
				od1 = this.orderService.addOrderedData(od);
				if (od1 != null) {
					this.cartService.deleteData(cart.getId());
				} else {
					rv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_CART);					
				}

			}
			
			m.addAttribute(BUY_STATUS, BUY_STATUS_MESSAGE);
			m.addAttribute(JspConstants.TITLE,TITLE_BUY);
			List<OrderDetail> data=this.orderService.getDataByOrderId(oid);
			m.addAttribute("size",data.size());
			
			m.addAttribute(ORDER_DETAIL, data);
			int total_amount=0;
			for (OrderDetail orderDetail : data) {
				total_amount=total_amount+(int)(orderDetail.getCartPrice());
			}
			m.addAttribute(TOTAL_AMOUNT, total_amount);
			rv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_TOKEN);

		} else {
			rv.setViewName(UrlMappingConstants.HOTEL_MERCHANT_USER_LOGIN);
			m.addAttribute(JspConstants.TITLE, LOGIN_TITLE);
		}

		return rv;
	}

}
