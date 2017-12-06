package com.spring.technician.entity;

import java.util.List;

public class Instruction {

	private String id;
	private List<String> descriptionList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getDescriptionList() {
		return descriptionList;
	}
	public void setDescriptionList(List<String> descriptionList) {
		this.descriptionList = descriptionList;
	}
	
	
}
