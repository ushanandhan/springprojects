package com.spring.technician.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.technician.entity.WOrder;

@Service
public class WOrderService {

	public WOrder createWorkOrder(WOrder order) {
		 String auth = "infosys:password";
		byte[] encodedAuth = Base64.encode( 
	            auth.getBytes(Charset.forName("US-ASCII")) );
	    String authHeader = "Basic " + new String( encodedAuth );
		HttpHeaders headers = new HttpHeaders();
		headers.set( "Authorization", authHeader );
		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WOrder> response = restTemplate.exchange("http://localhost:8080/service/worder/WRKORD0001?name=infosy", HttpMethod.GET, request, WOrder.class);
		return response.getBody();
	}
	
	public WOrder getWorkOrder(String id)  {
		ObjectMapper mapper = new ObjectMapper();
		WOrder order = null;
		if(id.equals("WRKORD0001")) {
			try {
				order = mapper.readValue(new FileInputStream("src/test/resources/WOrder.json"),WOrder.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			order.setWorkOrderId("WRKORD0001");
		}
		return order;
	}
	
}
