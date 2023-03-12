package com.example.demo.match;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.Team.TeamAdapter;
import com.example.demo.Team.*;

public class MatchAdapter {
       
	public MatchDto matchDaoToDto(MatchEntity mdao) {
		MatchDto mdto = new MatchDto();
		mdto.setDate(mdao.getDate());
		List<TeamDto> ls = new ArrayList<>();
		if(mdao.getTeams().size()>0) {
			TeamAdapter ta = new TeamAdapter();
			mdao.getTeams().forEach(team -> {
				ls.add(ta.teamDaoToDto(team));
			});
			mdto.setTeams(ls);
		}
		return mdto;
	}
	
    public MatchEntity matchDtoToDao(MatchDto mdto) {
    	MatchEntity mdao = new MatchEntity();
		mdao.setDate(mdto.getDate());
		Set<TeamEntity> set = new HashSet<>();
		if(mdto.getTeams().size()>0) {
			TeamAdapter ta = new TeamAdapter();
			mdto.getTeams().forEach(team -> {
				set.add(ta.teamDtoToDao(team));
			});
			mdao.setTeams(set);
		}
		return mdao;
	}
}
