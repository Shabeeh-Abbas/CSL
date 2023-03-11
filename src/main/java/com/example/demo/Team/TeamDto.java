package com.example.demo.Team;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.example.demo.response.ResponseData;
import com.example.demo.user.UserDto;
import com.example.demo.user.UserEntity;

public class TeamDto implements ResponseData{
     
	private String game;
	
	private String teamName;

	private Integer count;

	private List<UserDto> players;

	public String getGame() {
		return game;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public List<UserDto> getPlayers() {
		return players;
	}

	public void setPlayers(List<UserDto> players) {
		this.players = players;
	}
}
