package com.example.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.entity.ApiRequest;
import com.example.service.ApiRequestService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ApiRequestControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
    private ApiRequestService apiRequestService;
	
	@Test
	public void testGetHome() throws Exception {
		ApiRequest apiRequest = new ApiRequest();
		apiRequest.setId(new Long(1));
		apiRequest.setRequestTime(new Date());
		
		doNothing().when(apiRequestService).create(apiRequest);
		
		this.mvc.perform(get("/")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isMap())
		.andExpect(jsonPath("$.status").value("success"));
	}

	@Test
	public void testGetAllApiRequests() throws Exception {
		ApiRequest apiRequest = new ApiRequest();
		apiRequest.setId(new Long(1));
		apiRequest.setRequestTime(new Date());
		List<ApiRequest> mockApiRequestList = new ArrayList<ApiRequest>();
		mockApiRequestList.add(apiRequest);
		
		given(apiRequestService.getAllApiRequests()).willReturn(mockApiRequestList);
		
		this.mvc.perform(get("/getAllApiRequests")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$.[0]").isMap());
	}

}
