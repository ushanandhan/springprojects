package com.ushan.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:spring-context.xml"})
public class UserDAOTest {

	@Autowired
	UserDAO userDAO;
	
	@Test
	public void createUserTest(){
		BasicDBObject user = new BasicDBObject();
		user.put("_id", 12323);
		user.put("name", "ushan");
		user.put("age", 30);
		userDAO.saveUser(user,"USER");
	}
}
