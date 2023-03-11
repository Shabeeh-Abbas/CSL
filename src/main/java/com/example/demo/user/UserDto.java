package com.example.demo.user;

import java.util.List;

import com.example.demo.response.ResponseData;
import com.example.demo.role.RoleDto;


public class UserDto implements ResponseData{

	private String username;
	
	
	private String password;
	
	
	private String email;
	
	
	private List<RoleDto> userRoles;


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


	public List<RoleDto> getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(List<RoleDto> userRoles) {
		this.userRoles = userRoles;
	}

}
