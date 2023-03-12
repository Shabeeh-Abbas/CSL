package com.example.demo.match;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.Scoreboard.Scoreboard;
import com.example.demo.Team.*;

@Table(name = "Match_Table")
@Entity
public class MatchEntity {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Match_id")
	private Long id;

	@Column(name="Date")
	private String date;
	
	@Column(name="Match_Name")
	private String matchName;

	@ManyToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name="Team_Match_table",
		joinColumns = @JoinColumn(name="Team_id"),
		inverseJoinColumns = @JoinColumn(name="Match_id") 
	)
	private Set<TeamEntity> teams;
	
	@OneToOne(mappedBy="match")
	private Scoreboard scoreboard;
	
	public String getMatch() {
		return matchName;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
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

	public Set<TeamEntity> getTeams() {
		return teams;
	}

	public void setTeams(Set<TeamEntity> teams) {
		this.teams = teams;
	}
}
