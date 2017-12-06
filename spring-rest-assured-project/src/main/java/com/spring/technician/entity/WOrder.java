package com.spring.technician.entity;

public class WOrder {

	private String workOrderId;
	private String workDescription;
	private String workType;
	private Instruction instruction;
	private String cost;
	private Address location;
	private Technician technicianAssinged;
	private Store store;
	
	public String getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}
	public String getWorkDescription() {
		return workDescription;
	}
	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public Address getLocation() {
		return location;
	}
	public void setLocation(Address location) {
		this.location = location;
	}
	public Technician getTechnicianAssinged() {
		return technicianAssinged;
	}
	public void setTechnicianAssinged(Technician technicianAssinged) {
		this.technicianAssinged = technicianAssinged;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
	
}
