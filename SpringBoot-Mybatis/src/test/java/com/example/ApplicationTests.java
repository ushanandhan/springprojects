package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.User;
import com.example.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	 @Autowired
	    private UserMapper userMapper;
	    @Test
	    public void findAllUsers() {
	        List<User> users = userMapper.findAllUsers();
	        assertNotNull(users);
	        assertTrue(!users.isEmpty());
	    }
	    @Test
	    public void findUserById() {
	        User user = userMapper.findUserById(1);
	        assertNotNull(user);
	    }
	    @Test
	    public void createUser() {
	        User user = new User(0, "Siva", "siva@gmail.com");
	        userMapper.insertUser(user);
	        User newUser = userMapper.findUserById(user.getId());
	        assertEquals("Siva", newUser.getName());
	        assertEquals("siva@gmail.com", newUser.getEmail());
	    }
}
