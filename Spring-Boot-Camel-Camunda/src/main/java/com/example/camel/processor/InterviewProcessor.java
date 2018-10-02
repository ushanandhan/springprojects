package com.example.camel.processor;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.example.camel.model.Candidate;

@Component("interviewProcessor")
public class InterviewProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Candidate candidate = (Candidate) exchange.getIn().getBody();
		Map<String, Object> processVariables = new HashMap<>();
		processVariables.put("candidate", candidate);
		exchange.getIn().setBody(processVariables);
	}

}
