package com.example;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component("interviewProcessor")
public class InterviewProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String candidateId = (String) exchange.getIn().getBody();
		Map<String, Object> processVariables = new HashMap<>();
		processVariables.put("candidateId", candidateId);
		exchange.getIn().setBody(processVariables);
	}

}
