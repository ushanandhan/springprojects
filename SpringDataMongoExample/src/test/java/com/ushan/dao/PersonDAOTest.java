package com.ushan.dao;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ushan.bean.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:spring-context.xml"})
public class PersonDAOTest {

	@Autowired
	PersonDAO personDAO;
	
//	@BeforeClass
//	public static void setUp(){
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
//		personDAO = (PersonDAO)ctx.getBean("personDAO");
//	}
	
	@Test
//	@Ignore
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
		Person person = personDAO.getPersonById("5826eb89b6161b4501c81e84");
		assertNotNull(person);
	}

	@Test
	@Ignore
	public void testDeletePerson() {
		int count = personDAO.deletePerson("5826eb89b61678ad9ac479a8");
		assertTrue(count>0);
	}

}
