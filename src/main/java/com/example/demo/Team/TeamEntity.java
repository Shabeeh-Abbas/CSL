package com.example.demo.Team;

import java.util.Set;

import com.example.demo.match.MatchEntity;
import com.example.demo.user.UserEntity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "Team_Table")
@Entity
public class TeamEntity {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Team_id")
	private Long tid;
	
	@Column(name="team_Name")
	private String teamName;

	@Column(name="team_Game")
	private String game;
	
	@Column(name="no_of_Players")
	private Integer count;

	@OneToMany(mappedBy="team",cascade= {CascadeType.MERGE, CascadeType.PERSIST} ,fetch = FetchType.LAZY)
	private Set<UserEntity> players;
	
	@ManyToOne
	@JoinColumn(name="match_Id")
	private MatchEntity matchId;

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

	public Set<UserEntity> getPlayers() {
		return players;
	}

	public void setPlayers(Set<UserEntity> players) {
		this.players = players;
	}
}
