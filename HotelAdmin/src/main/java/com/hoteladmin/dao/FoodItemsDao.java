package com.hoteladmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hoteladmin.model.FoodItems;
import com.hoteladmin.repository.FoodItemsRepo;


@Repository("userDao")
public class FoodItemsDao {
	@Autowired
	private FoodItemsRepo foodItemsrepo;
	
	

	public void saveFood(FoodItems food) {
		this.foodItemsrepo.save(food);
		
	}

	

}
