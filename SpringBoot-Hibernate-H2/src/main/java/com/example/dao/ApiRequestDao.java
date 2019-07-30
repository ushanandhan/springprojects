package com.example.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.entity.ApiRequest;

@Repository
public class ApiRequestDao {
	@PersistenceContext
	private EntityManager entityManager;


	public void create(ApiRequest apiRequest) {
		entityManager.persist(apiRequest);
	}


	public void update(ApiRequest apiRequest) {
		entityManager.merge(apiRequest);
	}


	public ApiRequest getApiRequestById(long id) {
		return entityManager.find(ApiRequest.class, id);
	}


	public void delete(long id) {
		ApiRequest apiRequest = getApiRequestById(id);
		if (apiRequest != null) {
			entityManager.remove(apiRequest);
		}
	}