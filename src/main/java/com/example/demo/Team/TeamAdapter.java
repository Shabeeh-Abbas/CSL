package com.example.demo.Team;
import com.example.demo.user.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeamAdapter {
       
	  public TeamDto teamDaoToDto(TeamEntity tDao) {
		   TeamDto tdto = new TeamDto();
		   tdto.setGame(tDao.getGame());
		   tdto.setCount(tDao.getCount());
		   tdto.setTeamName(tDao.getTeamName());
		   UserAdapter ua = new UserAdapter();
		   if(tDao.getPlayers()!=null && tDao.getPlayers().size()>0) {
			   List<UserDto> ls = new ArrayList<>();
			   tDao.getPlayers().forEach(uDao -> {
				   ls.add(ua.userDaoToDto(uDao));
			   });
			   tdto.setPlayers(ls);
		   }
		   return tdto;
	  }
	  
	  public TeamEntity teamDtoToDao(TeamDto tDto) {
		   TeamEntity tdao = new TeamEntity();
		   tdao.setGame(tDto.getGame());
		   tdao.setCount(tDto.getCount());
		   tdao.setTeamName(tDto.getTeamName());
		   UserAdapter ua = new UserAdapter();
		   if(tDto.getPlayers()!=null && tDto.getPlayers().size()>0) {
			   Set<UserEntity> ls = new HashSet<>();
			   tDto.getPlayers().forEach(uDao -> {
				   ls.add(ua.userDtoToDao(uDao));
			   });
			   tdao.setPlayers(ls);
		   }
		   return tdao;
	  }
}
