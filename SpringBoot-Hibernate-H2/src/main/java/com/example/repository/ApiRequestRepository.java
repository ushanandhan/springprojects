package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.ApiRequest;

public interface ApiRequestRepository extends CrudRepository<ApiRequest, Long> {

	public List<ApiRequest> findAll();
}
