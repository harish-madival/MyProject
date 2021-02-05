package com.hotel.service;

import java.util.Date;
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

	public List<OrderDetail> geOrderedData() {
		// TODO Auto-generated method stub
		return this.orderFoodDao.getOrderedData();
	}

	public List<OrderDetail> getSingleData(Date oddDate) {
		// TODO Auto-generated method stub
		return this.orderFoodDao.getdataByDatewise(oddDate);
	}


}
