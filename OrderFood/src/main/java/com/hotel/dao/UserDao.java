package com.hotel.dao;

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

import com.hotel.model.Cart;
import com.hotel.model.FoodItems;
import com.hotel.model.User;
import com.hotel.repository.CartRepo;
import com.hotel.repository.FoodItemsRepo;
import com.hotel.repository.UserRepo;


@Repository("userDao")
public class UserDao {
	
	
	@Autowired
	private UserRepo userrepo;	

	public User validateUser(String emailid, String userpassword) {
		
		return this.userrepo.findByEmailidAndUserpassword(emailid, userpassword);
	}

	public void createUser(User u) {
		userrepo.save(u);
	}

	public User getUserData(int uid) {
		// TODO Auto-generated method stub
		return this.userrepo.findByUserId(uid);
	}

	public List<User> getAllUserData() {
		// TODO Auto-generated method stub
		return this.userrepo.findAll();
	}

	public User updateData(User user) {
		return this.userrepo.saveAndFlush(user);
	}

	public void deleteUser(int uid) {
		this.userrepo.deleteById(uid);		
	}

	

	

	

	
	
	
	
	

	

}
