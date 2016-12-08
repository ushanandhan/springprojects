package com.ushan.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class AbstractDAO {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public void createJsonObject(Object objectToSave,String collectionName){
		mongoTemplate.save(objectToSave, collectionName);
	}
}
