package com.example.camunda.component;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("interviewProcessEngine")
public class InterviewProcessEngine {

	@Autowired
	RuntimeService runtimeService;
	
	public void submitApplication(Execution execution) {
		String candidateId = (String) runtimeService.getVariable(execution.getProcessInstanceId(), "candidateId");
		System.out.println("Application for "+candidateId+" is successfully Submitted....");
	}
	
	public boolean isCalledForInterview(Execution execution) {
		boolean isCalled = false;
		String candidateId = (String) runtimeService.getVariable(execution.getProcessInstanceId(), "candidateId");
		if(candidateId.startsWith("CTS")) {
			isCalled = true;
		}
		return isCalled;
	}
	
	public void attendInterview(Execution execution) {
		System.out.println("Successfully attened interview.....");
	}
	
}
