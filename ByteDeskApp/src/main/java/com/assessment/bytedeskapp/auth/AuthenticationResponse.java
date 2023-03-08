package com.assessment.bytedeskapp.auth;

public class AuthenticationResponse {
	
	public AuthenticationResponse(String token, String firstName, String lastName, String userName, String password) {
		
		Token = token;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
	public AuthenticationResponse(String token) {
		Token = token;
	}
	public AuthenticationResponse() {	
	}

	private String Token;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
