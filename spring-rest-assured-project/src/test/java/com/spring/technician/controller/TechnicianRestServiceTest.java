package com.spring.technician.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.technician.entity.WOrder;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest
public class TechnicianRestServiceTest {


	
	
	/**
	 * This test is to validate Json path value
	 */
	@Test
	public void validateCreateOrder() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		WOrder body = mapper.readValue(new FileInputStream("src/test/resources/WOrder.json"),WOrder.class);
		RestAssured.baseURI  = "http://localhost:8080/service/worder/";
		given().
			auth().basic("infosys", "password").
			contentType("application/json").
			body(body).
		when().
			post().
		then().
			statusCode(200).
			body("workType", equalTo("Manual"));
	}
	
	
	/**
	 * This test is to validate Basic Authentication via Rest assured and ensured 401 is captured when Authentication is failed
	 */
	@Test
	public void validateCreateOrderWithoutAuthendication() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		WOrder body = mapper.readValue(new FileInputStream("src/test/resources/WOrder.json"),WOrder.class);
		RestAssured.baseURI  = "http://localhost:8080/service/worder/";
		given().
			contentType("application/json").
			body(body).
		when().
			post().
		then().
			statusCode(401);
	}
	
	
	/**
	 * This test is to validate Negative case of not having valid type in WorkType path using not() method.
	 */
	@Test
	public void validateNotAutomaticCreateOrder() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		WOrder body = mapper.readValue(new FileInputStream("src/test/resources/WOrder.json"),WOrder.class);
		RestAssured.baseURI  = "http://localhost:8080/service/worder/";
		given().
			auth().basic("infosys", "password").
			contentType("application/json").
			body(body).
		when().
			post().
		then().
			statusCode(200).
			body("workType", not("Automatic"));
	}
	
	
	/**
	 * This test is to validate Multiple items present in json path "instruction.descriptionList" with hasItems() method.
	 */
	@Test
	public void validateInstructionCreateOrder() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		WOrder body = mapper.readValue(new FileInputStream("src/test/resources/WOrder.json"),WOrder.class);
		RestAssured.baseURI  = "http://localhost:8080/service/worder/";
		given().
			auth().basic("infosys", "password").
			contentType("application/json").
			body(body).
		when().
			post().
		then().
			statusCode(200).
			body("instruction.descriptionList", hasItems("instruction 1","instruction 3"));
	}
	
	
	/**
	 * This test is to validate in one method to check mutilple Json Path items. We can Detach from root and then check child values.
	 */
	@Test
	public void validateCreateOrderDetachRoot() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		WOrder body = mapper.readValue(new FileInputStream("src/test/resources/WOrder.json"),WOrder.class);
		RestAssured.baseURI  = "http://localhost:8080/service/worder/";
		given().
			auth().basic("infosys", "password").
			contentType("application/json").
			body(body).
		when().
			post().
		then().
			statusCode(200).root("technicianAssinged.address").
			body("addressId", is("ADD0001289")).
			detachRoot("address").body("technicianType", is("Manual"));
	}
	
	
	/**
	 * This test is to validate unsupported content type error. 
	 */
	@Test
	public void validateCreateOrder415Error() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		RestAssured.baseURI  = "http://localhost:8080/service/worder/";
		given().
			auth().basic("infosys", "password").
			header("AppKey","Key-value").param("first_name", "first").param("second_name", "second").
		when().
			post().
		then().
			statusCode(415).log().all();
	}
	
	
	/**
	 * This test is to validate the response after extracting it from the response body. 
	 */
	@Test
	public void validateCreateOrderExtractResponse() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		WOrder body = mapper.readValue(new FileInputStream("src/test/resources/WOrder.json"),WOrder.class);
		RestAssured.baseURI  = "http://localhost:8080/service/worder/";
		Response response = given().
			auth().basic("infosys", "password").
			contentType("application/json").
			body(body).
		when().
			post().
		then().
			statusCode(200).root("technicianAssinged.address").
			body("addressId", is("ADD0001289")).
			extract().
			response();
		
		System.out.println("Content Type : "+response.getContentType());
	}
	
	
	/**
	 * This test is to validate method by sending path param values. 
	 */
	@Test
	public void validateGetOrderPathParam() {
		given().
			auth().basic("infosys", "password").
			param("name", "infosy").
		when().
			get("http://localhost:8080/service/worder/WRKORD0001").
		then().
			assertThat().
			body("workOrderId", equalTo("WRKORD0001"));
	}
	
	
	/**
	 * This test is to validate Not - Found error
	 */
	@Test
	public void validateGetOrderWithNotFound() {
		given().
			auth().basic("infosys", "password").
			param("name", "infosy").
		when().
			get("http://localhost:8080/service/worder/WRKORD0002").
		then().
			assertThat().
			statusCode(404).
			body("errorMessage", equalTo("Unable to get. order with id WRKORD0002 not found."));
	}

	/**
	 * This test is to validate groovy is support. Example. findAll { it.price < 10 }.title)
	 * where 'it' is for current object in the loop.
	 */
	@Test
	public void validateBookPriceUnder10() {
		given().
			auth().basic("infosys", "password").
			param("name", "infosy").
		when().
			get("http://localhost:8080/service/worder/WRKORD0001").
		then().
	       body("store.book.findAll { it.price < 10 }.title", hasItems("Sayings of the Century", "Moby Dick"));
	}
}
