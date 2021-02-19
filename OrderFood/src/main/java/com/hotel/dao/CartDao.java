package com.hotel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.model.Cart;
import com.hotel.repository.CartRepo;

@Repository("cartDao")
public class CartDao {
	@Autowired
	private CartRepo cartRepo;
	

	public void addtocart(Cart cart) {
		this.cartRepo.save(cart);
	}

	public void deleteCartedData(int id) {
		this.cartRepo.deleteById(id);
	}

	public List<Cart> getCartDataById(int uid) {
		return this.cartRepo.getCartedData(uid);
	}

}
