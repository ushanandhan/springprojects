package com.ushan.dao;

import com.mongodb.BasicDBObject;

public interface UserDAO {

	public void saveUser(BasicDBObject user,String collectionName);
}
