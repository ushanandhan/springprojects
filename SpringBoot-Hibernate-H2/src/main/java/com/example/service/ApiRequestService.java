package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ApiRequestDao;
import com.example.entity.ApiRequest;

@Service
@Transactional
public class ApiRequestService {

	@Autowired
	private ApiRequestDao apiRequestDao;

	public void create(ApiRequest apiRequest) {
		apiRequestDao.create(apiRequest);
	}
}