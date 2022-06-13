package com.test.models;

public class LoginPojo {
	private String username;
	private String password;
	
	public LoginPojo(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
