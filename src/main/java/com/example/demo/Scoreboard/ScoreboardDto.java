package com.example.demo.Scoreboard;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.demo.match.MatchDto;
import com.example.demo.match.MatchEntity;
import com.example.demo.response.ResponseData;

public class ScoreboardDto implements ResponseData{

	private String winner;
	
	private String loser;
	
	private Integer winnerScore;
	
	private Integer loserScore;

	private MatchDto match;
	
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

	public MatchDto getMatch() {
		return match;
	}

	public void setMatch(MatchDto match) {
		this.match = match;
	}
}
