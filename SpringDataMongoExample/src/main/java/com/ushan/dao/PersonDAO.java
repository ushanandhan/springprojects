package com.ushan.dao;

import com.ushan.bean.Person;

public interface PersonDAO {

	public void createPerson(Person person);
	public void updatePerson(Person person);
	public Person getPersonById(String id);
	public int deletePerson(String id);
	
}
