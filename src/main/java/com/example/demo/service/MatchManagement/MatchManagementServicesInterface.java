package com.example.demo.service.MatchManagement;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.example.demo.match.MatchDto;
import com.example.demo.response.*;

public interface MatchManagementServicesInterface {
	
	 ResponseData setMatch(MatchDto mdto) throws EntityExistsException;
	 
	 ResponseData setCompetingTeams(String match, String teamName) throws EntityNotFoundException;
	 
	 ResponseData setMatchWinner(String match, String teamName) throws EntityNotFoundException;

	 List<ResponseData> getMatches() throws EntityNotFoundException;
	 
	 String getMatchDate(String match) throws EntityNotFoundException;
	 
	 String getMatchWinner(String match) throws EntityNotFoundException;
	 
	 List<String> getCompetingTeams(String match) throws EntityNotFoundException;
	 
	 List<String> getWinnerTeamMembers(String match) throws EntityNotFoundException;
	 
}	 
