package com.hoteladmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoteladmin.model.OrderDetail;


public interface OrderFoodRepo extends JpaRepository<OrderDetail, Integer> {

}
