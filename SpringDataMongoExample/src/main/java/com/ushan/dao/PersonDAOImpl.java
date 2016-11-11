package com.ushan.dao;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;
import com.ushan.bean.Person;

public class PersonDAOImpl implements PersonDAO {

	MongoOperations mongoOps;
	
	public PersonDAOImpl(MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}
	
	@Override
	public void createPerson(Person person) {
		this.mongoOps.insert(person);
	}

	@Override
	public void updatePerson(Person person) {
		this.mongoOps.save(person);
	}

	@Override
	public Person getPersonById(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, Person.class);
	}

	@Override
	public int deletePerson(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query,Person.class);
		return result.getN();
	}

}
