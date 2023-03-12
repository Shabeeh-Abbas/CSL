package com.example.demo.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Team.TeamDto;
import com.example.demo.match.MatchDto;
import com.example.demo.response.Response;
import com.example.demo.response.ResponseData;
import com.example.demo.service.MatchManagement.MatchManagementServicesImplementation;

@RestController
@RequestMapping("/csl-1.0/rest/api")
@CrossOrigin(origins ="*")
public class MatchManagementController {
     
	@Autowired
	public MatchManagementServicesImplementation services;
	
	@PostMapping("/setMatch")
	public ResponseEntity<Response> setMatch(@RequestBody MatchDto mdto){
		ResponseData resData = null;
		try {
			resData = services.setMatch(mdto);
		} catch(EntityNotFoundException e) {
			Response r = new Response();
			r.setError("Match not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		} 
		Response res = new Response();
		res.setData(resData);
		res.setError(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	} 
	
	@PostMapping("/setCompetingTeams")
	public ResponseEntity<Response> setCompetingTeams(@RequestBody MatchDto mdto ,@RequestBody TeamDto tdto){
		ResponseData resData = null;
		try {
			resData = services.setCompetingTeams(mdto.getMatch(),tdto.getTeamName());
		} catch(EntityNotFoundException e){
			Response r = new Response();
			r.setError("Match or Team not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response res = new Response();
		res.setData(resData);
		res.setError(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
	
//	@PostMapping("/setMatchWinner")
//	public ResponseEntity<Response> setMatchWinner(@RequestParam String match, @RequestParam String teamName){
//		ResponseData resData = null;
//		try {
//			resData = services.setMatchWinner(match,teamName);
//		} catch(EntityNotFoundException e) {
//			Response r = new Response();
//			r.setError("Match or team not found");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
//		}
//		Response res = new Response();
//		res.setData(resData);
//		res.setError(null);
//		return ResponseEntity.status(HttpStatus.CREATED).body(res);
//	}
	
	@GetMapping("/getMatches")
	public ResponseEntity<Response> getMatches(){
		List<String> ls = null;
		try {
			ls = services.getAllMatches();
		} catch(EntityNotFoundException e) {
			Response r = new Response();
			r.setError("No Matches found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response res = new Response();
		res.setResponseDataCollection(ls);
		res.setError(null);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	} 
	
	@GetMapping("/getMatchDate")
	public  ResponseEntity<Response> getMatchDate(@RequestBody MatchDto mdto){
		String s = null;
		try {
			s = services.getMatchDate(mdto.getDate());
		} catch (EntityNotFoundException e) {
			Response r = new Response();
			r.setError("No Matches found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response res = new Response();
		res.setData(s);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}  
	
//	@GetMapping("/getMatchWinner")
//	public ResponseEntity<Response> getMatchWinner(@RequestParam String match){
//		String s = null;
//		try {
//			s = services.getMatchWinner(match);
//		} catch(EntityNotFoundException e) {
//			Response r = new Response();
//			r.setError("Match not found");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
//		}
//		Response res = new Response();
//		res.setData(s);
//		return ResponseEntity.status(HttpStatus.OK).body(res);
//	}
	
	@GetMapping("/getCompetingTeams")
	public ResponseEntity<Response> getCompetingTeams(@RequestBody MatchDto mdto){
		List<String> ls = null;
		try {
			ls = services.getCompetingTeams(mdto.getMatch());
		} catch(EntityNotFoundException e) {
			Response r = new Response();
			r.setError("Match not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response res = new Response();
		res.setResponseDataCollection(ls);
		res.setError(null);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	} 
	
//	@GetMapping("/getWinnerTeamMembers")
//	public ResponseEntity<Response> getWinnerTeamMembers(@RequestParam String match){
//		List<String> ls = null;
//		try {
//			ls = services.getWinnerTeamMembers(match);
//		} catch(EntityNotFoundException e) {
//			Response r = new Response();
//			r.setError("Match not found");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
//		}
//		Response res = new Response();
//		res.setResponseDataCollection(ls);
//		res.setError(null);
//		return ResponseEntity.status(HttpStatus.OK).body(res);
//	} 
	
}
