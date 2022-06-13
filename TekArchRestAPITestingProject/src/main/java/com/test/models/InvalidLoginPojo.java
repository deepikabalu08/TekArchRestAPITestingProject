package com.test.models;

public class InvalidLoginPojo {
	private String username;
	private String invalidpassword;
	
	public InvalidLoginPojo(String username, String invalidpassword) {
		this.username = username;
		this.invalidpassword = invalidpassword;
	}

	public String getUsername() {
		return username;
	}

	public String getInvalidPassword() {
		return invalidpassword;
	}

}