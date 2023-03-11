package com.example.demo.service.MatchManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Team.TeamAdapter;
import com.example.demo.Team.TeamEntity;
import com.example.demo.match.MatchAdapter;
import com.example.demo.match.MatchDto;
import com.example.demo.match.MatchEntity;
import com.example.demo.repo.MatchRepo;
import com.example.demo.repo.TeamRepo;
import com.example.demo.response.ResponseData;

@Service
public class MatchManagementServicesImplementation implements MatchManagementServicesInterface{
	
	@Autowired
	public MatchRepo mrepo;
	
	@Autowired
	public TeamRepo trepo;

	@Override
	public ResponseData setMatch(MatchDto mdto) throws EntityExistsException{
		// TODO Auto-generated method stub
		Optional<MatchEntity> opme = mrepo.findBymatchName(mdto.getMatch());
		if(opme.isPresent()) {
			throw new EntityExistsException();
		}
		MatchAdapter ma = new MatchAdapter(); 
		MatchEntity me = ma.matchDtoToDao(mdto);
		mrepo.save(me);
		return mdto;
	}

	@Override
	public ResponseData setCompetingTeams(String match, String teamName) {
		// TODO Auto-generated method stub
		Optional<TeamEntity> opte = trepo.findByteamName(teamName);
		Optional<MatchEntity> opme = mrepo.findBymatchName(match);
		if(opte.isPresent() && opme.isPresent()) {
			opme.get().getTeams().add(opte.get());
			mrepo.save(opme.get());
			MatchAdapter ma = new MatchAdapter();
			MatchDto mdto = ma.matchDaoToDto(opme.get());
			return mdto;
		} else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public ResponseData setMatchWinner(String match, String teamName) throws EntityNotFoundException{
		// TODO Auto-generated method stub
		Optional<TeamEntity> opte = trepo.findByteamName(teamName);
		Optional<MatchEntity> opme = mrepo.findBymatchName(match);
		if(opte.isPresent() && opme.isPresent()) {
		       opme.get().setWinner(opte.get().getTeamName());
		       mrepo.save(opme.get());
		       MatchAdapter ma = new MatchAdapter();
			   MatchDto mdto = ma.matchDaoToDto(opme.get());
			   return mdto;
		} else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public List<ResponseData> getMatches() throws EntityNotFoundException{
		// TODO Auto-generated method stub
		Iterable<MatchEntity> itme = mrepo.findAll();
		if(itme!=null) {
			List<ResponseData> ls = null;
			MatchAdapter ma = new MatchAdapter();
			itme.forEach(match -> {
				ls.add(ma.matchDaoToDto(match));
			});
			return ls;
		} else {
			throw new EntityNotFoundException();
		}
		
	}

	@Override
	public String getMatchDate(String match) throws EntityNotFoundException{
		// TODO Auto-generated method stub
		Optional<MatchEntity> opme = mrepo.findBymatchName(match);
		if(opme.isPresent()) {
			return opme.get().getDate();
		}
		else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public String getMatchWinner(String match) throws EntityNotFoundException{
		// TODO Auto-generated method stub
		Optional<MatchEntity> opme = mrepo.findBymatchName(match);
		if(opme.isPresent() && opme.get() !=null && opme.get().getWinner()!=null) {
			return opme.get().getWinner();
		}
		else {
			throw new EntityNotFoundException();
		}
	}


	@Override
	public List<String> getCompetingTeams(String match) throws EntityNotFoundException{
		// TODO Auto-generated method stub
		Optional<MatchEntity> opme = mrepo.findBymatchName(match);
		if(opme.isPresent() && opme.get() !=null && opme.get().getTeams()!=null && opme.get().getTeams().size()>0) {
			List<String> ls = new ArrayList<>();
			opme.get().getTeams().forEach(team -> {
				ls.add(team.getTeamName());
			});
			return ls;
		}
		else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public List<String> getWinnerTeamMembers(String match) throws EntityNotFoundException{
		// TODO Auto-generated method stub
		Optional<MatchEntity> opme = mrepo.findBymatchName(match);
		Optional<TeamEntity> opte = trepo.findByteamName(opme.get().getWinner());
		List<String> ls = null;
		if(opme.isPresent() && opme.get() !=null && opte.get()!=null && opte.isPresent() && opte.get().getPlayers().size()>0 && opte.get().getPlayers()!=null){
			opte.get().getPlayers().forEach(players -> {
				ls.add(players.getUsername());
			});
			return ls;
		} else {
			throw new EntityNotFoundException();
		}
	}

}
