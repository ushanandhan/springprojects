package com.example.camunda.component;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("interviewProcessEngine")
public class InterviewProcessEngine {

	@Autowired
	RuntimeService runtimeService;
	
	public void SubmitApplication(Execution execution) {
		String candidateId = (String) runtimeService.getVariable(execution.getProcessInstanceId(), "candidateId");
		System.out.println("Application for "+candidateId+" is successfully Submitted....");
	}
}
