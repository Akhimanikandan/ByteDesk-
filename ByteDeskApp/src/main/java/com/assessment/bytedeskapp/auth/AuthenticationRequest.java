package com.assessment.bytedeskapp.auth;

public class AuthenticationRequest {
 
	public AuthenticationRequest(String userName, String password) {
		
		this.userName = userName;
		this.password = password;
	}

	
public AuthenticationRequest() {
		
		
	}

	
	private String userName;
	
	private String password;

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
