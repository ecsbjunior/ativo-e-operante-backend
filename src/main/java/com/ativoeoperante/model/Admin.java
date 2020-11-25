package com.ativoeoperante.model;

public class Admin {
	private int id;
	private String username, password, apikey;
	
	public Admin(int id, String username, String password, String apikey) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.apikey = apikey;
	}
	
	public Admin(String username, String password, String apikey) {
		this(0, username, password, apikey);
	}
	
	public Admin() {
		this("", "", "");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getApiKey() {
		return apikey;
	}
	
	public void setApiKey(String apikey) {
		this.apikey = apikey;
	}
}
