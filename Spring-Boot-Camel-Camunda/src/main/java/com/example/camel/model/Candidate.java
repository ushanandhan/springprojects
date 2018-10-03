package com.example.camel.model;

import java.io.Serializable;

public class Candidate implements Serializable{

	private String candidateId;
	private String firstName;
	private String lastName;
	private String email;
	private boolean calledForInterview;
	private boolean interviewCleared;
	private boolean offerAccepted;
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isCalledForInterview() {
		return calledForInterview;
	}
	public void setCalledForInterview(boolean calledForInterview) {
		this.calledForInterview = calledForInterview;
	}
	public boolean isInterviewCleared() {
		return interviewCleared;
	}
	public void setInterviewCleared(boolean interviewCleared) {
		this.interviewCleared = interviewCleared;
	}
	public boolean isOfferAccepted() {
		return offerAccepted;
	}
	public void setOfferAccepted(boolean offerAccepted) {
		this.offerAccepted = offerAccepted;
	}
	
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", isCalledForInterview=" + calledForInterview + ", isInterviewCleared="
				+ interviewCleared + ", isOfferAccepted=" + offerAccepted + "]";
	}
	
}
