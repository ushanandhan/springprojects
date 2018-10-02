package com.example.camel.model;

import java.io.Serializable;

public class Candidate implements Serializable{

	private String candidateId;
	private String firstName;
	private String lastName;
	private String email;
	private boolean isCalledForInterview;
	private boolean isInterviewCleared;
	private boolean isOfferAccepted;
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
		return isCalledForInterview;
	}
	public void setCalledForInterview(boolean isCalledForInterview) {
		this.isCalledForInterview = isCalledForInterview;
	}
	public boolean isInterviewCleared() {
		return isInterviewCleared;
	}
	public void setInterviewCleared(boolean isInterviewCleared) {
		this.isInterviewCleared = isInterviewCleared;
	}
	public boolean isOfferAccepted() {
		return isOfferAccepted;
	}
	public void setOfferAccepted(boolean isOfferAccepted) {
		this.isOfferAccepted = isOfferAccepted;
	}
	
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", isCalledForInterview=" + isCalledForInterview + ", isInterviewCleared="
				+ isInterviewCleared + ", isOfferAccepted=" + isOfferAccepted + "]";
	}
	
}
