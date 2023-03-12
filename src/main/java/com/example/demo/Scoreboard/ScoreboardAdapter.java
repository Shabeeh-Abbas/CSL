package com.example.demo.Scoreboard;

import com.example.demo.match.MatchAdapter;

public class ScoreboardAdapter {
      
	public ScoreboardDto scoreboardDaoToDto(Scoreboard sdao) {
		ScoreboardDto sdto = new ScoreboardDto();
		sdto.setLoser(sdao.getLoser());
		sdto.setLoserScore(sdao.getLoserScore());
		sdto.setWinner(sdao.getWinner());
		sdto.setWinnerScore(sdao.getWinnerScore());
		MatchAdapter ma = new MatchAdapter();
		sdto.setMatch(ma.matchDaoToDto(sdao.getMatch()));
		
		return sdto;
	}
	
	public Scoreboard scoreboardDtoToDao(ScoreboardDto sdto) {
		Scoreboard sdao = new Scoreboard();
		sdao.setLoser(sdto.getLoser());
		sdao.setLoserScore(sdto.getLoserScore());
		sdao.setWinner(sdto.getWinner());
		sdao.setWinnerScore(sdto.getWinnerScore());
		MatchAdapter ma = new MatchAdapter();
		sdao.setMatch(ma.matchDtoToDao(sdto.getMatch()));
		
		return sdao;
	}
}
