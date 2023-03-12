package com.example.demo.service.UserManagement;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.exception.checked.IllegalRoleValueException;
import com.example.demo.exception.checked.InvalidCredentialsException;
import com.example.demo.exception.checked.PasswordIncorrectException;
import com.example.demo.exception.checked.RoleFoundException;
import com.example.demo.exception.checked.RoleNotFoundException;
import com.example.demo.exception.checked.RoleReassignmentException;
import com.example.demo.exception.checked.RoleUnavailableException;
import com.example.demo.exception.checked.UserUnavailableException;
import com.example.demo.exception.unchecked.*;

import com.example.demo.response.ResponseData;
import com.example.demo.role.RoleDto;
import com.example.demo.user.UserDto;

public interface LoginServicesInterface {
      
	ResponseData saveUser(UserDto user) throws UserRegisteredException;
	
	List<String> getRoles() throws RoleNotFoundException;
	
	List<String> postRoles(RoleDto rdto) throws RoleFoundException;
	
//	ResponseData saveRole(RoleDto role) throws RoleAvailbaleException;
	
	ResponseData updateUserRole(UserDto udto) throws RoleReassignmentException,IllegalRoleValueException;
	
	ResponseData getUser(String username, String password) throws InvalidCredentialsException;
	
	void removeUser(String username);
	
	List<String> getAdmins() throws RoleNotFoundException;
	
	List<String> getCaptains() throws RoleNotFoundException;
	
	List<String> getPlayers() throws RoleNotFoundException;
	
	List<String> getUsernames() throws UserUnavailableException;
	
	ResponseData updateUsername(String oldUsername, String newUsername, String password) throws PasswordIncorrectException,UserUnavailableException;
	
	ResponseData updatePassword(String username, String currentPassword, String newPassword) throws PasswordIncorrectException,UserUnavailableException;
	
	ResponseData updateEmail(String username, String password , String newEmail) throws PasswordIncorrectException,UserUnavailableException;
	
//	ResponseData removeUserRole(String username, String role) throws UserUnavailableException;
}
