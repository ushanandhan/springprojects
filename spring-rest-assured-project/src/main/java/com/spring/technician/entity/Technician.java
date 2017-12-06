package com.spring.technician.entity;

public class Technician {

	private String technicianId;
	private String name;
	private Address address;
	private String technicianType;
	private String jobType;
	public String getTechnicianId() {
		return technicianId;
	}
	public void setTechnicianId(String technicianId) {
		this.technicianId = technicianId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getTechnicianType() {
		return technicianType;
	}
	public void setTechnicianType(String technicianType) {
		this.technicianType = technicianType;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
}
