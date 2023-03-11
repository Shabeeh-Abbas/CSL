package com.example.demo.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.role.RoleAdapter;
import com.example.demo.role.RoleDto;
import com.example.demo.role.Roles;

public class UserAdapter {
      
	public UserEntity userDtoToDao(UserDto userDto) {
		UserEntity userDao = new UserEntity();
		userDao.setUsername(userDto.getUsername());
		userDao.setEmail(userDto.getEmail());
		userDao.setPassword(userDto.getPassword());
		if(userDto.getUserRoles()!=null) {
			Set<Roles> set = new HashSet<>();
			RoleAdapter ra = new RoleAdapter();
			userDto.getUserRoles().forEach(role -> {
				set.add(ra.roleDtoToDao(role));
			});
		}
		return userDao;
	}
	
	public UserDto userDaoToDto(UserEntity userDao) {
		UserDto userDto = new UserDto();
		userDto.setUsername(userDao.getUsername());
		userDto.setEmail(userDao.getEmail());
		userDto.setPassword(userDao.getPassword());
		if(userDao.getUserRoles()!=null) {
			List<RoleDto> set = new ArrayList<>();
			RoleAdapter ra = new RoleAdapter();
			userDao.getUserRoles().forEach(role -> {
				set.add(ra.roleDaoToDto(role));
			});
		}
		return userDto;
	}
}
