package com.ativoeoperante.model;

public class Feedback {
	private int id;
	private String description;
	private Complaint complaint;
	
	public Feedback(int id, String description, Complaint complaint) {
		this.id = id;
		this.description = description;
		this.complaint = complaint;
	}
	
	public Feedback(String description, Complaint complaint) {
		this(0, description, complaint);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Complaint getComplaint() {
		return complaint;
	}
	
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
}
