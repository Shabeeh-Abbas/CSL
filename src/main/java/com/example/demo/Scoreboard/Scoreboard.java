package com.example.demo.Scoreboard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.demo.match.MatchEntity;

@Entity
public class Scoreboard {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Winner")
	private String winner;
	
	@Column(name="Loser")
	private String loser;
	
	@Column(name="Winner_Score")
	private Integer winnerScore;
	

	@Column(name="Loser_Score")
	private Integer loserScore;
	
	@OneToOne
	@JoinColumn(name="match_id")
	private MatchEntity match;
	
	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getLoser() {
		return loser;
	}

	public void setLoser(String loser) {
		this.loser = loser;
	}

	public Integer getWinnerScore() {
		return winnerScore;
	}

	public void setWinnerScore(Integer winnerScore) {
		this.winnerScore = winnerScore;
	}

	public Integer getLoserScore() {
		return loserScore;
	}

	public void setLoserScore(Integer loserScore) {
		this.loserScore = loserScore;
	}

	public MatchEntity getMatch() {
		return match;
	}

	public void setMatch(MatchEntity match) {
		this.match = match;
	}

}
