package com.example.demo.match;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.Team.*;

@Table(name = "Match_Table")
@Entity
public class MatchEntity {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Match_id")
	private Long mid;

	@Column(name="Date")
	private String date;
	
	@Column(name="Match_Name")
	private String matchName;
   
	@Column(name="Winner")
	private String winner;

	@OneToMany(mappedBy = "matchId", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TeamEntity> teams;
	
	public String getMatch() {
		return matchName;
	}

	public void setMatch(String match) {
		this.matchName = match;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public Set<TeamEntity> getTeams() {
		return teams;
	}

	public void setTeams(Set<TeamEntity> teams) {
		this.teams = teams;
	}
}
