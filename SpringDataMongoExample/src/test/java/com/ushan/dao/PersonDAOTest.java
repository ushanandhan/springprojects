package com.ushan.dao;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ushan.bean.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:spring-context.xml"})
public class PersonDAOTest {

	@Autowired
	PersonDAO personDAO;
	
	@Test
	public void testCreatePerson() {
		Person person = new Person(null,"Ushan" , "Trivandrum");
		personDAO.createPerson(person);
	}

	@Test
	@Ignore
	public void testUpdatePerson() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetPersonById() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testDeletePerson() {
		fail("Not yet implemented");
	}

}
