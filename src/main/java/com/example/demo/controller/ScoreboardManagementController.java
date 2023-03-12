package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Scoreboard.ScoreboardAdapter;
import com.example.demo.Scoreboard.ScoreboardDto;
import com.example.demo.exception.checked.MatchNotFoundException;
import com.example.demo.exception.checked.ScoreboardNotFoundException;
import com.example.demo.match.MatchDto;
import com.example.demo.response.Response;
import com.example.demo.response.ResponseData;
import com.example.demo.service.ScoreboardManagement.ScoreboardManagementServicesImplementaion;

@RestController
@RequestMapping("/csl-1.0/rest/api")
@CrossOrigin(origins ="*")
public class ScoreboardManagementController{

	@Autowired
	public ScoreboardManagementServicesImplementaion services;
	
	@GetMapping("/getscoreboard")
	public ResponseEntity<Response> getScoreboard(@RequestBody MatchDto mdto){
		ResponseData resData = null;
		try {
			resData = services.getScoreboard(mdto.getMatch());
		} catch (MatchNotFoundException e){
			Response r = new Response();
			r.setError("Scoreboard for the match not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		} 
		  Response r = new Response();
		  r.setData(resData);
		  return ResponseEntity.status(HttpStatus.OK).body(r);
	}
	
	@GetMapping("/getallscoreboards")
	public ResponseEntity<Response> getAllScoreboards(){
         List<ResponseData> ls = null;
         try {
        	 ls = services.getAllScoreboards();
         } catch (ScoreboardNotFoundException e) {
			// TODO: handle exception
        	 Response r = new Response();
 			r.setError("No scoreboards found");
 			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
         Response r = new Response();
		 r.setResponseDataCollection(ls);
		 return ResponseEntity.status(HttpStatus.OK).body(r);
    }
	
	@PostMapping("/setscoreboardbeforematch")
	public ResponseEntity<Response> setScoreboardBeforeMatch(@RequestBody MatchDto mdto){
		ResponseData resData = null;
		try {
			resData = services.setScoreboardBeforeMatch(mdto.getMatch());
		} catch(MatchNotFoundException e) {
			Response r = new Response();
 			r.setError("Match not found");
 			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
		Response r = new Response();
		 r.setData(resData);
		 return ResponseEntity.status(HttpStatus.OK).body(r);
	} 
	
	@PostMapping("/setscoreboardaftermatch")
	public ResponseEntity<Response> setScoreboardAfterMatch(@RequestBody ScoreboardDto sdto){
		ResponseData resData = null;
		try {
			ScoreboardAdapter sa = new ScoreboardAdapter();
			resData = services.setScoreboardAfterMatch(sdto);
		} catch(ScoreboardNotFoundException e) {
			Response r = new Response();
 			r.setError("Scoreboard not found");
 			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		} 
		Response r = new Response();
		 r.setData(resData);
		 return ResponseEntity.status(HttpStatus.OK).body(r);
	} 
}