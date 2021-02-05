package com.hoteladmin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hoteladmin.model.OrderDetail;
import com.hoteladmin.repository.OrderFoodRepo;


@Repository("orderFoodDao")
public class OrderFoodDao {
	@Autowired
	private OrderFoodRepo orderFoodRepo;

	public List<OrderDetail> getOrderedData() {
		// TODO Auto-generated method stub
		return this.orderFoodRepo.findAll();
	}
	
}
