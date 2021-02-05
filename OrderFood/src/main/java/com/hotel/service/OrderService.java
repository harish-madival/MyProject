package com.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dao.OrderFoodDao;
import com.hotel.model.Cart;
import com.hotel.model.OrderDetail;

@Service
public class OrderService {
	
	@Autowired
	private OrderFoodDao orderFoodDao;
	
	public OrderDetail addOrderedData(OrderDetail od) {
		return this.orderFoodDao.addOrderedData(od);
	}

	public List<OrderDetail> getDataByOrderId(long oid) {
		return this.orderFoodDao.getDataByOrderId(oid);
	}


}
