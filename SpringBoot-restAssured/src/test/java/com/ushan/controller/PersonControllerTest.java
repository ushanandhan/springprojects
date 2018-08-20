package com.ushan.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	@LocalServerPort
	int port;
	
	@Test
	public void testGetAllPersons() {
		given().
			port(port).
		when().
			get("/service").
		then().
			statusCode(200).
			body("firstName", hasItem("Charles"));
				
	}

}
