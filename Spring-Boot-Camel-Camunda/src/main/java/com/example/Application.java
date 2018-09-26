package com.example;

import org.apache.camel.CamelContext;
import org.camunda.bpm.camel.spring.CamelServiceImpl;
import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Autowired
	ProcessEngine processEngine;
	
	@Autowired
	CamelContext camelContext;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CamelServiceImpl camelServiceImpl() {
		CamelServiceImpl camelServiceImpl = new CamelServiceImpl();
		camelServiceImpl.setProcessEngine(processEngine);
		camelServiceImpl.setCamelContext(camelContext);
		return camelServiceImpl;
	}
}
