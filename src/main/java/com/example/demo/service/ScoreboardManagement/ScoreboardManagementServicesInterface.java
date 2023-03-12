package com.example.demo.service.ScoreboardManagement;

import java.util.List;

import com.example.demo.Scoreboard.ScoreboardDto;
import com.example.demo.exception.checked.MatchNotFoundException;
import com.example.demo.exception.checked.ScoreboardNotFoundException;
import com.example.demo.response.ResponseData;

public interface ScoreboardManagementServicesInterface {

	ResponseData getScoreboard(String match) throws MatchNotFoundException;
	
	List<ResponseData> getAllScoreboards() throws ScoreboardNotFoundException;
	
	ResponseData setScoreboardBeforeMatch(String match) throws MatchNotFoundException;
	
	ResponseData setScoreboardAfterMatch(ScoreboardDto sdto) throws ScoreboardNotFoundException;
	
	
}
