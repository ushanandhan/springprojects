package com.byteslounge.spring.tx.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byteslounge.spring.tx.dao.UserDAO;
import com.byteslounge.spring.tx.model.User;

@Service
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public User getUserById(int userId) {
		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}
	
	public User getUser(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :username");
		query.setParameter("username", username);
		return (User) query.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return criteria.list();
	}

}
