package com.example.demo.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class UserAdapter {
      
	public UserEntity userDtoToDao(UserDto userDto) {
		UserEntity userDao = new UserEntity();
		userDao.setUsername(userDto.getUsername());
		userDao.setEmail(userDto.getEmail());
		userDao.setPassword(userDto.getPassword());
		userDao.setUserRole(userDto.getUserRole());
		return userDao;
	}
	
	public UserDto userDaoToDto(UserEntity userDao) {
		UserDto userDto = new UserDto();
		userDto.setUsername(userDao.getUsername());
		userDto.setEmail(userDao.getEmail());
		userDto.setPassword(userDao.getPassword());
		userDto.setUserRole(userDao.getUserRole());;
		return userDto;
	}
}
