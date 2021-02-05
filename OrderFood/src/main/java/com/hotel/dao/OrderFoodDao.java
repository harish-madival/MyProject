package com.hotel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.model.Cart;
import com.hotel.model.OrderDetail;
import com.hotel.repository.OrderFoodRepo;

@Repository("orderFoodDao")
public class OrderFoodDao {
	@Autowired
	private OrderFoodRepo orderFoodRepo;
	
	public OrderDetail addOrderedData(OrderDetail od) {
		return this.orderFoodRepo.save(od);
	}

	public List<OrderDetail> getDataByOrderId(long oid) {
		return this.orderFoodRepo.findByorderid(oid);
	}

}
