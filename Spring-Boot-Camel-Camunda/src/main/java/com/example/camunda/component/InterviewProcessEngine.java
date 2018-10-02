package com.example.camunda.component;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.camel.model.Candidate;

@Component("interviewProcessEngine")
public class InterviewProcessEngine {

	@Autowired
	RuntimeService runtimeService;
	
	public void submitApplication(Execution execution) {
		Candidate candidate = (Candidate) runtimeService.getVariable(execution.getProcessInstanceId(), "candidate");
		System.out.println("Application for "+candidate.getFirstName()+" is successfully Submitted....");
	}
	
	public boolean isCalledForInterview(Execution execution) {
		boolean isCalled = false;
		Candidate candidate = (Candidate) runtimeService.getVariable(execution.getProcessInstanceId(), "candidate");
		if(candidate.isCalledForInterview()) {
			isCalled = true;
		}
		return isCalled;
	}
	
	public void attendInterview(Execution execution) {
		Candidate candidate = (Candidate) runtimeService.getVariable(execution.getProcessInstanceId(), "candidate");
		System.out.println("Application for "+candidate.getFirstName()+" Successfully attended interview.....");
	}
	
	public boolean isInterviewCleared(Execution execution) {
		boolean isCleared = false;
		Candidate candidate = (Candidate) runtimeService.getVariable(execution.getProcessInstanceId(), "candidate");
		if(candidate.isInterviewCleared()) {
			isCleared = true;
		}
		return isCleared;
	}
	
	public void reviewOffer(Execution execution) {
		Candidate candidate = (Candidate) runtimeService.getVariable(execution.getProcessInstanceId(), "candidate");
		System.out.println("Applicant "+candidate.getFirstName()+" Reviewing offer.");
	}
	
	public boolean isOfferAccepted(Execution execution) {
		boolean isAccepted = false;
		Candidate candidate = (Candidate) runtimeService.getVariable(execution.getProcessInstanceId(), "candidate");
		if(candidate.isOfferAccepted()) {
			isAccepted = true;
		}
		return isAccepted;
	}
	
	
	public void submitAcceptenceForm(Execution execution) {
		Candidate candidate = (Candidate) runtimeService.getVariable(execution.getProcessInstanceId(), "candidate");
		System.out.println("Applicant "+candidate.getFirstName()+" accepted the offer.....");
	}
	
	public void offerRejected(Execution execution) {
		Candidate candidate = (Candidate) runtimeService.getVariable(execution.getProcessInstanceId(), "candidate");
		System.out.println("Applicant "+candidate.getFirstName()+" rejected the offer.....");
	}
	
	public void applicationRejected(Execution execution) {
		Candidate candidate = (Candidate) runtimeService.getVariable(execution.getProcessInstanceId(), "candidate");
		System.out.println("Applicantion for "+candidate.getFirstName()+" rejected.....");
	}
}
