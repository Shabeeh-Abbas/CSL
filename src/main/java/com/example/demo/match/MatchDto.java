package com.example.demo.match;

import java.util.List;

import com.example.demo.Scoreboard.Scoreboard;
import com.example.demo.Scoreboard.ScoreboardDto;
import com.example.demo.Team.TeamDto;
import com.example.demo.response.ResponseData;


public class MatchDto implements ResponseData{
    
	private String date;
	
	private String match;

	private List<TeamDto> teams;
	
	private ScoreboardDto scoreboardDto;

	public String getDate() {
		return date;
	}
	
	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<TeamDto> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamDto> teams) {
		this.teams = teams;
	}
}
