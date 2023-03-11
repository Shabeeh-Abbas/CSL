package com.example.demo.role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.user.UserAdapter;
import com.example.demo.user.UserDto;
import com.example.demo.user.UserEntity;

public class RoleAdapter {
	
	public RoleDto roleDaoToDto(Roles roleDao) {
		  RoleDto roleDto = new RoleDto();
		  roleDto.setRole(roleDao.getRole());
		  if(roleDao.getUsers().size()>0 && roleDao.getUsers()!=null) {
			  UserAdapter ua = new UserAdapter();
			  List<UserDto> ls = new ArrayList<>();
			  roleDao.getUsers().forEach(user -> {
				  ls.add(ua.userDaoToDto(user));
			  });
			  roleDto.setUsers(ls);
		  }
		  return roleDto;
	}
	
	public Roles roleDtoToDao(RoleDto roleDto) {
		  Roles roleDao = new Roles();
		  roleDao.setRole(roleDto.getRole());
		  if(roleDto.getUsers().size()>0 && roleDto.getUsers()!=null) {
			  UserAdapter ua = new UserAdapter();
			  Set<UserEntity> set = new HashSet<>();
			  roleDto.getUsers().forEach(user -> {
				  set.add(ua.userDtoToDao(user));
			  });
			  roleDao.setUsers(set);
		  }
		  return roleDao;
	}

}
