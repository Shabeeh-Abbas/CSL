package com.example.demo.service.TeamManagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Team.TeamAdapter;
import com.example.demo.Team.TeamDto;
import com.example.demo.Team.TeamEntity;
import com.example.demo.exception.checked.NoPlayersPresentException;
import com.example.demo.exception.checked.NoTeamsPresentException;
import com.example.demo.exception.checked.TeamAvailableException;
import com.example.demo.repo.TeamRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.response.ResponseData;
import com.example.demo.user.UserEntity;

@Service
public class TeamManagementServicesImplementation implements TeamManagementServicesInterface {
    
	@Autowired
	public TeamRepo trepo;
	
	@Autowired
	public UserRepo urepo;
	
	@Override
	public ResponseData saveTeam(TeamDto tdto) throws TeamAvailableException{
		// TODO Auto-generated method stub
		Optional<TeamEntity> opte = trepo.findByteamName(tdto.getTeamName());
		if(opte.isEmpty()) {
			TeamAdapter ta = new TeamAdapter();
			TeamEntity te = ta.teamDtoToDao(tdto);
			te.setCount(0);
			trepo.save(te);
			return tdto;
		} else {
			throw new TeamAvailableException();
		}
		
	}
	
	@Override
	public ResponseData getTeam(String team) throws NoSuchElementException{
		// TODO Auto-generated method stub
		Optional<TeamEntity> tDao = trepo.findByteamName(team);
		TeamAdapter ta = new TeamAdapter();
		TeamDto tDto = null;
		if(tDao.isPresent()) {
		     tDto = ta.teamDaoToDto(tDao.get());
			 return tDto;
		}else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public List<ResponseData> getAllTeams() throws NoTeamsPresentException{
		// TODO Auto-generated method stub
		Iterable<TeamEntity> itTeams = trepo.findAll();
		Optional<Iterable<TeamEntity>> opItTeams = Optional.of(itTeams);
		List<ResponseData> ls = new ArrayList<>();
		TeamAdapter ta = new TeamAdapter();
		if(opItTeams.isPresent()) {
			opItTeams.get().forEach(team ->{
				ls.add(ta.teamDaoToDto(team));
			});
			return ls;	
		} else {
			throw new NoTeamsPresentException();
		}
		
	}

	@Override
	public ResponseData addPlayer(String team, String player) throws NoSuchElementException{
		// TODO Auto-generated method stub
	     Optional<UserEntity> opue = urepo.findByusername(player);
	     Optional<TeamEntity> opte = trepo.findByteamName(team);
	     if(opte.isPresent() && opue.isPresent()) {
	    	 UserEntity ue = opue.get();
	    	 TeamEntity te = opte.get();
	    	 Set<UserEntity> set = new HashSet<>();
	    	 set.add(ue);
	    	 te.getPlayers().forEach(players -> {
	    		 set.add(players);
	    	 });
	    	 te.setPlayers(set);
	    	 te.setCount(te.getCount()+1);
	    	 trepo.save(te);
	    	 TeamAdapter ta = new TeamAdapter();
	    	 return ta.teamDaoToDto(te);
	     } else {
	    	 throw new NoSuchElementException();
	     }
		
	}

	@Override
	public ResponseData remPlayer(String team, String player) throws NoSuchElementException,NoPlayersPresentException{
		// TODO Auto-generated method stub
		Optional<UserEntity> opue = urepo.findByusername(player);
	     Optional<TeamEntity> opte = trepo.findByteamName(team);
	     if(opte.isPresent() && opue.isPresent()) {
	    	 UserEntity ue = opue.get();
	    	 TeamEntity te = opte.get();
	    	 if(te.getPlayers().size()>0) {
	    		 Set<UserEntity> set = new HashSet<>();
		    	 te.getPlayers().forEach(players -> {
		    		 if(!players.getUsername().equals(player)){
		    			 set.add(players);
		    		 }
		    	 });
		    	 te.setPlayers(set);
		    	 te.setCount(te.getCount()-1);
		    	 trepo.save(te);
		    	 TeamAdapter ta = new TeamAdapter();
		    	 return ta.teamDaoToDto(te);
	    	 } else {
	    		 throw new NoPlayersPresentException();
	    	 }
	    	 
	     } else {
	    	 throw new NoSuchElementException();
	     }
	}

	
	@Override
	public List<String> getTeamNames() throws NoTeamsPresentException{
		// TODO Auto-generated method stub
		Iterable<TeamEntity> itTeams = trepo.findAll();
		Optional<Iterable<TeamEntity>> opItTeams = Optional.of(itTeams);
		List<String> ls = new ArrayList<>();
		if(opItTeams.isPresent()){
			opItTeams.get().forEach(team -> {
				ls.add(team.getTeamName());
			});
			return ls;
		} else {
			throw new NoTeamsPresentException();
		}
		
	}

	@Override
	public List<String> getTeamPlayerNames(String team) throws NoTeamsPresentException,NoPlayersPresentException {
		// TODO Auto-generated method stub
		Optional<TeamEntity> opte=trepo.findByteamName(team);
		List<String> ls = null;
		if(opte.isPresent() && opte.get()!=null) {
			if(opte.get().getPlayers().size()==0 || opte.get().getPlayers()==null){
				throw new NoPlayersPresentException();
			} else {
				opte.get().getPlayers().forEach(players ->{
					ls.add(players.getUsername());
				});
				return ls;
			}
		} else {
			throw new NoTeamsPresentException();
		}
	}
}
