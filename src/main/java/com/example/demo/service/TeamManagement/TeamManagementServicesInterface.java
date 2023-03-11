package com.example.demo.service.TeamManagement;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.Team.TeamDto;
import com.example.demo.exception.checked.NoPlayersPresentException;
import com.example.demo.exception.checked.NoTeamsPresentException;
import com.example.demo.exception.checked.TeamAvailableException;
import com.example.demo.response.ResponseData;

public interface TeamManagementServicesInterface {
     
	ResponseData getTeam(String team) throws NoSuchElementException;
	
	List<ResponseData> getAllTeams() throws NoTeamsPresentException;
	
	ResponseData addPlayer(String team , String player) throws NoSuchElementException;
	
	ResponseData remPlayer(String team , String player) throws NoSuchElementException,NoPlayersPresentException;
	
	ResponseData saveTeam(TeamDto tdto) throws  TeamAvailableException;
	
	List<String> getTeamNames() throws NoTeamsPresentException;
	
	List<String> getTeamPlayerNames(String team) throws NoTeamsPresentException,NoPlayersPresentException;
}
