package com.example.demo.service.ScoreboardManagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Scoreboard.Scoreboard;
import com.example.demo.Scoreboard.ScoreboardAdapter;
import com.example.demo.Scoreboard.ScoreboardDto;
import com.example.demo.exception.checked.MatchFoundException;
import com.example.demo.exception.checked.MatchNotFoundException;
import com.example.demo.exception.checked.ScoreboardNotFoundException;
import com.example.demo.match.MatchEntity;
import com.example.demo.repo.MatchRepo;
import com.example.demo.repo.ScoreboardRepo;
import com.example.demo.response.ResponseData;
import com.example.demo.role.Roles;

@Service
public class ScoreboardManagementServicesImplementaion implements ScoreboardManagementServicesInterface{

	@Autowired
	public ScoreboardRepo srepo;
	
	@Autowired
	public MatchRepo mrepo;
	
	
	@Override
	public ResponseData getScoreboard(String match) throws MatchNotFoundException{
		// TODO Auto-generated method stub
		Optional<Scoreboard> ops = srepo.findBymatch(match);
		if(ops.isPresent()) {
			ScoreboardAdapter sa = new ScoreboardAdapter();
			return sa.scoreboardDaoToDto(ops.get());
		}else {
			throw new MatchNotFoundException();
		}
	}

	@Override
	public List<ResponseData> getAllScoreboards() throws ScoreboardNotFoundException{
		// TODO Auto-generated method stub
		Iterable<Scoreboard> its = srepo.findAll();
		List<ResponseData> ls = new ArrayList<>();
		ScoreboardAdapter sa = new ScoreboardAdapter();
		if(its!=null && ((Collection<Scoreboard>) its).size()>0) {
			its.forEach(s -> {
				ls.add(sa.scoreboardDaoToDto(s));
			});
			return ls;
		} else {
			throw new ScoreboardNotFoundException();
		}
		
	}

	@Override
	public ResponseData setScoreboardBeforeMatch(String match) throws MatchNotFoundException{
		// TODO Auto-generated method stub
		Optional<MatchEntity> opme = mrepo.findBymatchName(match);
		if(opme.isPresent()) {
			Scoreboard sb = new Scoreboard();
			ScoreboardAdapter sa = new ScoreboardAdapter();
			sb.setMatch(opme.get());
			srepo.save(sb);
			return sa.scoreboardDaoToDto(sb);
		} else {
			throw new MatchNotFoundException();
		}
		
	}

	@Override
	public ResponseData setScoreboardAfterMatch(ScoreboardDto sdto) throws ScoreboardNotFoundException{
		// TODO Auto-generated method stub
		Optional<Scoreboard> ops = srepo.findBymatch(sdto.getMatch().getMatch());
		if(ops.isPresent()) {
			ops.get().setLoser(sdto.getLoser());
			ops.get().setLoserScore(sdto.getLoserScore());
			ops.get().setWinner(sdto.getWinner());
			ops.get().setWinnerScore(sdto.getWinnerScore());
			srepo.save(ops.get());
			ScoreboardAdapter sa = new ScoreboardAdapter();
			return sa.scoreboardDaoToDto(ops.get());
		} else {
			throw new ScoreboardNotFoundException();
		}
		
	}
       
	
}
