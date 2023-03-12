package com.example.demo.user;

import java.util.List;

import com.example.demo.response.ResponseData;



public class UserDto implements ResponseData{

	private String username;
	
	
	private String password;
	
	
	private String email;
	
	
	private String role;


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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserRole() {
		return role;
	}


	public void setUserRole(String userRole) {
		this.role = userRole;
	}

}
