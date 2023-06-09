package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Team.TeamDto;
import com.example.demo.exception.checked.NoPlayersPresentException;
import com.example.demo.exception.checked.NoTeamsPresentException;
import com.example.demo.exception.checked.TeamAvailableException;
import com.example.demo.response.Response;
import com.example.demo.response.ResponseData;
import com.example.demo.service.TeamManagement.TeamManagementServicesImplementation;
import com.example.demo.user.UserDto;
import com.example.demo.user.UserEntity;

@RestController
@RequestMapping("/csl-1.0/rest/api")
@CrossOrigin(origins ="*")
public class TeamManagementController {
       
	@Autowired
	public TeamManagementServicesImplementation services;
	
	@GetMapping("/getTeam")
	public ResponseEntity<Response> getTeam(@RequestBody TeamDto tdto){
				ResponseData resData = null;
				try {
				    resData = services.getTeam(tdto.getTeamName());
				} catch(NoSuchElementException e){
					Response r = new Response();
					r.setError("Team not found");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
				}
				Response r = new Response();
				r.setData(resData);
				return ResponseEntity.status(HttpStatus.OK).body(r);
	}
		
	
	
	@PostMapping("/saveTeam")
	public ResponseEntity<Response> saveTeam(@RequestBody TeamDto tdto) throws NullPointerException{
		try {
			if(tdto==null) {
				throw new NullPointerException();
			} else {
			        try {
			        	if(tdto.getGame()!=null && tdto.getTeamName()!=null && tdto.getGame()!="" && tdto.getTeamName()!="") {
			        		ResponseData resData= null;
						      try {
						    	  resData = services.saveTeam(tdto);
						      }	catch(TeamAvailableException e) {
						    	  Response r = new Response();
								  r.setError("Team exists");
								  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
						      }
						      Response r = new Response();
							  r.setData(resData);
							  return ResponseEntity.status(HttpStatus.OK).body(r);
						} else {
							throw new NullPointerException();
						} 
			        } catch(NullPointerException e){
			        	Response r = new Response();
						r.setError("Paramters null or empty");
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
			        }	
			}
		}catch(NullPointerException e) {
			Response r = new Response();
			r.setError("Null Request");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
	}
	
	@GetMapping("/getAllTeams")
	public ResponseEntity<Response> getAllTeams(){
		        List<ResponseData> ls = null;
				try {
					 ls = services.getAllTeams();
				} catch(NoTeamsPresentException e) {
					Response r = new Response();
					r.setError("No Teams present");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
				}
				Response r = new Response();
				r.setResponseDataCollection(ls);
				return ResponseEntity.status(HttpStatus.OK).body(r);
	}
	
	@PostMapping("/addPlayer")
	public ResponseEntity<Response> addPlayer(@RequestBody TeamDto tdto ,@RequestBody  UserDto udto){
			
				ResponseData resData = null;
				try {
					resData = services.addPlayer(tdto.getTeamName(), udto.getUsername());
				} catch(NoSuchElementException e) {
					Response r = new Response();
					r.setError("Team or player not found");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
				}
				Response r = new Response();
				r.setData(resData);
				return ResponseEntity.status(HttpStatus.OK).body(r);
	}
	
	
	
	@DeleteMapping("/remPlayer")
	public ResponseEntity<Response> remPlayer(@RequestBody TeamDto tdto ,@RequestBody  UserDto udto){
		ResponseData resData = null;
		try {
			resData = services.remPlayer(tdto.getTeamName(), udto.getUsername());
		} catch(NoSuchElementException e) {
			    Response r = new Response();
				r.setError("Team or player not found");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		} catch(NoPlayersPresentException e) {
				Response r = new Response();
				r.setError("Players Not Found");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response r = new Response();
		r.setData(resData);
		return ResponseEntity.status(HttpStatus.OK).body(r);
	}
	
	@GetMapping("/getTeamNames")
	public ResponseEntity<Response> getTeamNames(){
		List<String> ls = null;
		try {
			ls = services.getTeamNames();
		} catch(NoTeamsPresentException e) {
			Response r = new Response();
			r.setError("No Teams Found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response r = new Response();
		r.setResponseDataCollection(ls);
		return ResponseEntity.status(HttpStatus.OK).body(r);
	}
	
	@GetMapping("/getTeamPlayerNames")
	public ResponseEntity<Response> getTeamPlayerNames(@RequestBody TeamDto tdto){
		List<String> ls = null;
		try {
			ls = services.getTeamPlayerNames(tdto.getTeamName());
		} catch(NoTeamsPresentException e) {
			Response r = new Response();
			r.setError("No Teams Found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		} catch(NoPlayersPresentException e) {
			Response r = new Response();
			r.setError("No Players Found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response r = new Response();
		r.setResponseDataCollection(ls);
		return ResponseEntity.status(HttpStatus.OK).body(r);
	}
}
