package com.ushan.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ushan.bean.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:spring-context.xml"})
public class PersonRepoTest{
	
	@Autowired
	PersonRepo personRepo;
	
	@Test
	@Ignore
	public void createPersonTest() {
		Person person = new Person(null,"Ariya" , "Trivandrum");
		personRepo.save(person);
	}
	
	@Test
	@Ignore
	public void getPersonByIdTest(){
		Person person = personRepo.findOne("5827204cb61691b2d05906c6");
		System.out.println("Person is : "+person.getName());
	}
	
	@Test
	@Ignore
	public void updatePersonTest(){
		Person person = personRepo.findOne("5827204cb61691b2d05906c6");
		person.setName("varadhan");
		personRepo.save(person);
	}
	
	@Test
	@Ignore
	public void deletePersonTest(){
		Person person = personRepo.findOne("5827204cb61691b2d05906c6");
		personRepo.delete(person);
	}
}
