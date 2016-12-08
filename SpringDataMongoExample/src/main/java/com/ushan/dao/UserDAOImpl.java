package com.ushan.dao;

import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

@Component
public class UserDAOImpl extends AbstractDAO implements UserDAO {

	@Override
	public void saveUser(BasicDBObject user, String collectionName) {
		super.createJsonObject(user, collectionName);
	}

}
