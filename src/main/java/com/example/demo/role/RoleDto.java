package com.example.demo.role;

import java.util.List;

import com.example.demo.response.ResponseData;
import com.example.demo.user.UserDto;


public class RoleDto implements ResponseData{

    private String role;
    
	public String getRole() {
	     return role;
    }

    public void setRole(String role) {
	     this.role = role;
    }
}
//	
//	private List<UserDto> users;
//

//
//	public List<UserDto> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<UserDto> users) {
//		this.users = users;
//	}
//}
