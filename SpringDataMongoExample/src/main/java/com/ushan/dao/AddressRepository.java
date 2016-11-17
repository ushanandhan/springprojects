package com.ushan.dao;

import org.springframework.data.repository.CrudRepository;

import com.ushan.bean.Address;

public interface AddressRepository extends CrudRepository<Address, String> {

}
