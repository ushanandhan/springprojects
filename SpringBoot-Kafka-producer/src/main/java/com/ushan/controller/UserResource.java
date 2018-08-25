package com.ushan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ushan.kafka.model.User;

@RestController
@RequestMapping("kafka")
public class UserResource {

	@Autowired
	KafkaTemplate<String, User> kafkaTemplate;
	private static final String TOPIC = "KAFKA_EXAMPLE";
	
	@GetMapping("/public/{name}")
	public String post(@PathVariable("name") final String name) {
			kafkaTemplate.send(TOPIC, new User(name, "Technolory", 12000L));
			return "Publised Successfully";
	}
}
