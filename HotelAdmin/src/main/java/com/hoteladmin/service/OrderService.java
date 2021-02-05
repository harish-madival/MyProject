package com.hoteladmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteladmin.dao.OrderFoodDao;
import com.hoteladmin.model.OrderDetail;


@Service
public class OrderService {
	
	@Autowired
	private OrderFoodDao orderFoodDao;

	public List<OrderDetail> getOrderedData() {
		// TODO Auto-generated method stub
		return this.orderFoodDao.getOrderedData();
	}
	

}
