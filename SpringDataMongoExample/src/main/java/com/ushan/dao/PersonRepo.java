package com.ushan.dao;

import org.springframework.data.repository.CrudRepository;

import com.ushan.bean.Person;

public interface PersonRepo extends CrudRepository<Person, String> {

}
