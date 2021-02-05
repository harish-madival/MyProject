package com.hotel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.model.OrderDetail;

public interface OrderFoodRepo extends JpaRepository<OrderDetail, Integer> {

	List<OrderDetail> findByorderid(long oid);

	List<OrderDetail> findByordereddate(Date oddDate);

}
