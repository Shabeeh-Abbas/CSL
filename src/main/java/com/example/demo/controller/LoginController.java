package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.checked.*;

import com.example.demo.exception.unchecked.*;

import com.example.demo.response.Response;
import com.example.demo.response.ResponseData;
import com.example.demo.role.RoleDto;
import com.example.demo.service.UserManagement.*;

import com.example.demo.user.UserDto;


@RestController
@RequestMapping("/csl-1.0/rest/api")
public class LoginController {

	@Autowired
	public LoginServicesImplementation services;
	
	@GetMapping("/getUsernames")
	public ResponseEntity<Response> getUsernames(){
		List<String> ls = null;
		try {
			 ls = services.getUsernames();
		} catch(UserUnavailableException e) {
			Response r = new Response();
			r.setError("Users not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		} 
		Response res = new Response();
		  res.setResponseDataCollection(ls);
		  res.setError(null);
		  return ResponseEntity.status(HttpStatus.OK).body(res);
		
	}
	
	@GetMapping("/getAdmins")
	public ResponseEntity<Response> getAdmins(){
		List<String> ls = null;
		try {
			ls = services.getAdmins();
		} catch(UserUnavailableException e) {
			Response r = new Response();
			r.setError("Admins not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		} catch(RoleNotFoundException e) {
			Response r = new Response();
			r.setError("Role not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response res = new Response();
		res.setResponseDataCollection(ls);
		res.setError(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
		
	}
	
	@GetMapping("/getCaptains")
	public ResponseEntity<Response> getCptains(){
		List<String> ls = null;
		try {
			ls = services.getCaptains();
		} catch(UserUnavailableException e) {
			Response r = new Response();
			r.setError("Captains not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		} catch(RoleNotFoundException e) {
			Response r = new Response();
			r.setError("Role not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response res = new Response();
		res.setResponseDataCollection(ls);
		res.setError(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
		
	}
	
	@GetMapping("/getPlayers")
	public ResponseEntity<Response> getPlayers(){
		List<String> ls = null;
		try {
			ls = services.getPlayers();
		} catch(UserUnavailableException e) {
			Response r = new Response();
			r.setError("Players not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		} catch(RoleNotFoundException e) {
			Response r = new Response();
			r.setError("Role not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response res = new Response();
		res.setResponseDataCollection(ls);
		res.setError(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
		
	}
	
	@PostMapping("/register/registeruser")
    public ResponseEntity<Response> saveUser(@RequestBody UserDto user){
		try {
			ResponseData resDto = null;
			if(user.getUsername()!=null && user.getPassword()!=null && user.getEmail()!=null && 
					user.getUsername()!="" && user.getEmail()!="" && user.getPassword()!="") {
				try {
					resDto = (ResponseData) services.saveUser(user);
				} catch(UserRegisteredException e){
					Response r = new Response();
					r.setError("User already registered");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
				}
			      Response res = new Response();
				  res.setData(resDto);
				  res.setError(null);
				  return ResponseEntity.status(HttpStatus.CREATED).body(res);
			} else {
				if(user.getUsername()==null || user.getUsername()=="") throw new UsernameNullException(); 			
				else if (user.getPassword()==null || user.getPassword()=="") throw new PasswordNullException();
				else throw new EmailNullException();
			}
		} catch(UsernameNullException e){
			Response res = new Response();
			res.setError("Username is null or empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		} catch(PasswordNullException e){
			Response res = new Response();
			res.setError("Password is null or empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		} catch(EmailNullException e){
			Response res = new Response();
			res.setError("Email is null or empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		} 
	}
    
	@PostMapping("/saveRole")
	public ResponseEntity<Response> saveRole(@RequestBody RoleDto role) {
		try {
			if(role.getRole()!=null && role.getRole()!="") {
				try {
					ResponseData resData = services.saveRole(role);
					Response res = new Response();
					res.setData(resData);
					return ResponseEntity.status(HttpStatus.CREATED).body(res);
				} catch(RoleAvailbaleException e) {
					Response res = new Response();
					res.setError("Role is present");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
				}
		
			} else {
				throw new IllegalRoleValueException();
			}
		} catch(IllegalRoleValueException e) {
			Response res = new Response();
			res.setError("Role is null or empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
	}
	
	@PostMapping("/saveRoleToUser")
	public ResponseEntity<Response> addRoleToUser(@RequestParam String username, @RequestParam String role) {
		try {
			if(username!=null && role!=null && username!="" && role!=""){
				ResponseData resData =null;
				try {
					 resData = services.addRoleToUser(username,role);
				} catch(NoSuchElementException e){
					Response res = new Response();
					res.setError("Username or Role unavailable");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
				} catch(RoleReassignmentException e) {
					Response res = new Response();
					res.setError("Role has already been assigned");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
				}
				Response res = new Response();
				res.setData(resData);
				return ResponseEntity.status(HttpStatus.OK).body(res);
				
			} else {
				throw new IllegalRoleOrUsernameValueException();
			}
		} catch(IllegalRoleOrUsernameValueException e) {
			Response res = new Response();
			res.setError("Role or Username null or empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		} 
	}
	
	@DeleteMapping("/removeUserRole")
	public ResponseEntity<Response> removeUserRole(String username, String role) {
		try {
			if(username!=null && role!=null && username!="" && role!=""){
				ResponseData resData = null;
				try {
					resData = services.removeUserRole(username, role);
				} catch(UserUnavailableException e){
					Response res = new Response();
					res.setError("Username Unavailable");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
				}
				Response res = new Response();
				res.setData(resData);
				return ResponseEntity.status(HttpStatus.OK).body(res);	
			} else {
				throw new IllegalRoleOrUsernameValueException();
			}
		} catch(IllegalRoleOrUsernameValueException e) {
			Response res = new Response();
			res.setError("Role or Username null or empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
		
	}
	
	@PostMapping("/login/loginuser")
	public ResponseEntity<Response> getUser(@RequestBody UserDto udto) {
		try {
			if((udto.getUsername()!=null && udto.getUsername()!="") && (udto.getPassword()!=null && udto.getPassword()!="")) {
				ResponseData resData = null;
				try {
					resData = services.getUser(udto.getUsername(),udto.getPassword());
				} catch(InvalidCredentialsException e) {
					Response res = new Response();
					res.setError("Invalid Credentials");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
				}
				Response res = new Response();
				res.setData(resData);
				return ResponseEntity.status(HttpStatus.OK).body(res);
			} else {
				throw new UsernameNullException();
			}
		} catch(UsernameNullException e) {
			Response res = new Response();
			res.setError("Username null or empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
		
	}
		
	
//	@DeleteMapping("/removeUser")
//	public void removeUser(@RequestParam String username) {
//		services.removeUser(username);NoSuchElementException,RoleReassignmentException
//	}
	
	@PatchMapping("/updateUsername")
	public ResponseEntity<Response> updateUsername(@RequestParam String oldUsername,@RequestParam String newUsername ,@RequestParam String password) {
		try {
			if(oldUsername!=null && password!=null && newUsername!=null && oldUsername!="" && password!="" && newUsername!="") {
				ResponseData resData = null;
			     try {
			    	 resData = services.updateUsername(oldUsername,newUsername,password);
			     } catch(PasswordIncorrectException e){
			    	    Response res = new Response();
						res.setError("Incorrect Password");
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
			     } catch(UserUnavailableException e){
			    	    Response res = new Response();
						res.setError("Username Unavailable");
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
			     }
			    Response res = new Response();
				res.setData(resData);
				return ResponseEntity.status(HttpStatus.OK).body(res);
			} else {
				  throw new InvalidCredentialsException();
			}
		} catch(InvalidCredentialsException e){
			Response res = new Response();
			res.setError("Username null or empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
	}
	
	@PatchMapping("/updatePassword")
	public ResponseEntity<Response> updatePassword(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
		try {
			if(username!=null && oldPassword!=null && newPassword!=null && username!="" && oldPassword!="" && newPassword!="") {
				ResponseData resData = null;
			     try {
			    	 resData = services.updatePassword(username, oldPassword, newPassword);
			     } catch(PasswordIncorrectException e){
			    	    Response res = new Response();
						res.setError("Incorrect Password");
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
			     } catch(UserUnavailableException e){
			    	    Response res = new Response();
						res.setError("Username Unavailable");
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
			     } 
			    Response res = new Response();
				res.setData(resData);
				return ResponseEntity.status(HttpStatus.OK).body(res);
			     
			} else {
				throw new InvalidCredentialsException();
			}
	    }
		catch(InvalidCredentialsException e){
			Response res = new Response();
			res.setError("Username null or empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		} 
	}
	
	@PatchMapping("/updateEmail")
	public ResponseEntity<Response> updateEmail(@RequestParam String username, @RequestParam String password, @RequestParam String newEmail) {
		try {
			if(username!=null && newEmail!=null && password!=null && username!="" && password!="" && newEmail!="") {
				ResponseData resData = null;
			     try {
			    	 resData = services.updateEmail(username,password,newEmail);
			     } catch(PasswordIncorrectException e){
			    	    Response res = new Response();
						res.setError("Incorrect Password");
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
			     } catch(UserUnavailableException e){
			    	    Response res = new Response();
						res.setError("Username Unavailable");
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
			     } 
			     Response res = new Response();
				 res.setData(resData);
				 return ResponseEntity.status(HttpStatus.OK).body(res);
			} else {
				throw new InvalidCredentialsException();
			}
		} catch(InvalidCredentialsException e){
			Response res = new Response();
			res.setError("Username null or empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
	}
}
