package com.ativoeoperante.model;

public class CompetentOrgan {
	private int id;
	private String name;
	
	public CompetentOrgan(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CompetentOrgan(String name) {
		this(0, name);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
