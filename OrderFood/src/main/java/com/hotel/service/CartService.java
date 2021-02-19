package com.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dao.CartDao;
import com.hotel.model.Cart;

@Service
public class CartService {
	@Autowired
	private CartDao cartDao;
	
	
	public void addtocart(Cart cart) {
		this.cartDao.addtocart(cart);
		
	}

	public void delete(int id) {
		this.cartDao.deleteCartedData(id);
	}


	public List<Cart> getAllCartedDataByUid(int uid) {
		return this.cartDao.getCartDataById(uid);
	}

	public void deleteData(int id) {
		this.cartDao.deleteCartedData(id);
	}
}
