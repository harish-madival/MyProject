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
		// TODO Auto-generated method stub
		this.cartRepo.save(cart);
	}

	public void deleteCartedData(int id) {
		// TODO Auto-generated method stub
		this.cartRepo.deleteById(id);
	}

	public List<Cart> getCartDataById(int uid) {
		// TODO Auto-generated method stub
		return this.cartRepo.findByUserid(uid);
	}

}
