package com.example.camel.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CamelController {

	@Autowired
	ProducerTemplate producerTemplate;
	
	@RequestMapping(value = "/camel")
	public void startCamel() {
		producerTemplate.sendBody("direct:firstRoute", "Calling via Spring Boot Rest Controller");
	}
	
	@RequestMapping(value="/applyForJob/{candidateId}")
	public void applyForJob(@PathVariable("candidateId") String candidateId) {
		producerTemplate.sendBody("direct:applyForJobRoute",candidateId);
	}
}
