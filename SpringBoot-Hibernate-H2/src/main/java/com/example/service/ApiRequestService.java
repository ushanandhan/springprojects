package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ApiRequestDao;
import com.example.entity.ApiRequest;
import com.example.repository.ApiRequestRepository;

@Service
@Transactional
public class ApiRequestService {

//	@Autowired
//	private ApiRequestDao apiRequestDao;
	
	@Autowired
	private ApiRequestRepository apiRequestRepository;

	public void create(ApiRequest apiRequest) {
//		apiRequestDao.create(apiRequest);
		apiRequestRepository.save(apiRequest);
	}
	
	public List<ApiRequest> getAllApiRequests(){
		return apiRequestRepository.findAll();
	}
}