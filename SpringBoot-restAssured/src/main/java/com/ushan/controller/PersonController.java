package com.ushan.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ushan.model.Person;

@RestController
@RequestMapping("/service")
public class PersonController {
	
	@GetMapping
	public List<Person> getAllPersons(){
		List<Person> people = Arrays.asList(
				new Person("Charles","Dickens", 60),
				new Person("Lewis","Carole", 42),
				new Person("Thomas","Carlyne", 51),
				new Person("Charlotte","Dewin", 45),
				new Person("Matthew","Angelo", 39)
				);
		return people;
	}

}
