package com.example.demo.service.UserManagement;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.Iterator;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Team.TeamEntity;
import com.example.demo.exception.checked.IllegalRoleValueException;
import com.example.demo.exception.checked.InvalidCredentialsException;
import com.example.demo.exception.checked.PasswordIncorrectException;
import com.example.demo.exception.checked.RoleFoundException;
import com.example.demo.exception.checked.RoleNotFoundException;
import com.example.demo.exception.checked.RoleReassignmentException;
import com.example.demo.exception.checked.RoleUnavailableException;
import com.example.demo.exception.checked.UserUnavailableException;
import com.example.demo.exception.unchecked.RoleAvailbaleException;
import com.example.demo.exception.unchecked.UserRegisteredException;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.response.ResponseData;
import com.example.demo.role.RoleAdapter;
import com.example.demo.role.RoleDto;
import com.example.demo.role.Roles;
//import com.example.demo.role.RoleAdapter;
//import com.example.demo.role.RoleDto;
//import com.example.demo.role.Roles;
import com.example.demo.user.UserAdapter;
import com.example.demo.user.UserDto;
import com.example.demo.user.UserEntity;

//import jakarta.transaction.Transactional;

@Service
public class LoginServicesImplementation implements LoginServicesInterface {
	
	@Autowired
	private UserRepo urepo;  
	
	@Autowired
	private RoleRepo rrepo;  
	
	@Override
	public ResponseData saveUser(UserDto udto) throws UserRegisteredException{
		// TODO Auto-generated method stub
		  ResponseData resData = null;
		  if(urepo.findByusername(udto.getUsername()).isEmpty()) {
			  udto.setUserRole("PLAYER");
			  UserAdapter ua = new UserAdapter();
			  UserEntity u = ua.userDtoToDao(udto);
			  urepo.save(u);
			  resData = ua.userDaoToDto(u);
			  return resData;
		  } else {
			    throw new  UserRegisteredException();
		  }
     }

//	@Override
//	public ResponseData saveRole(RoleDto rdto) throws RoleAvailbaleException{
//		// TODO Auto-generated method stub
//		Optional<Roles> opr = rrepo.findByrole(rdto.getRole());
//		if(opr.isEmpty()) {
//			ResponseData resData = null;
//			RoleAdapter ra = new RoleAdapter();
//			Roles r = ra.roleDtoToDao(rdto);
//			rrepo.save(r);
//			resData = ra.roleDaoToDto(r);
//			return resData;
//		} else {
//			throw new RoleAvailbaleException();
//		}
				
//	}

	@Override
	public ResponseData updateUserRole(UserDto udto) throws RoleReassignmentException,IllegalRoleValueException{
		// TODO Auto-generated method stub
		if(udto.getUserRole().equals("ADMIN") || udto.getUserRole().equals("PLAYER") || udto.getUserRole().equals("CAPTAIN")) {
			Optional<UserEntity>  u = urepo.findByusername(udto.getUsername());
			if(u.get().getUserRole().equals(udto.getUserRole())) {
				throw new RoleReassignmentException();
			} else {
				ResponseData resData = null;
				u.get().setUserRole(udto.getUserRole());
				urepo.save(u.get());
				UserAdapter ua = new UserAdapter();
				UserDto uDto = ua.userDaoToDto(u.get());
				return uDto;
			}
		}else {
			throw new IllegalRoleValueException();
		}
		
	}

	@Override
	public ResponseData getUser(String username, String password) throws InvalidCredentialsException{
		// TODO Auto-generated method stub
		Optional<UserEntity> u = urepo.findByusername(username);
		UserDto udto = null;
		if(u.isPresent() && u.get().getPassword().equals(password)){
			UserAdapter ua = new UserAdapter();
			udto = ua.userDaoToDto(u.get());
			return udto;
		} else {
			throw new InvalidCredentialsException();
		}
		
	}

	@Override
	@Transactional
	public void removeUser(String username) {
		// TODO Auto-generated method stub
		urepo.deleteByusername(username);
	}

	@Override
	public ResponseData updateUsername(String oldUsername, String newUsername,String password) throws PasswordIncorrectException,UserUnavailableException{
		// TODO Auto-generated method stub
		UserEntity u = urepo.findByusername(oldUsername).get();
		ResponseData resData = null;
		if(u.getPassword().equals(password)){
			UserEntity ue = u;
			ue.setPassword(newUsername);
			urepo.save(ue);
			UserAdapter ua = new UserAdapter ();
		
			UserDto udto = ua.userDaoToDto(ue);
			resData = udto;
			return resData;
		} else {
			if(!u.getPassword().equals(password)) throw new PasswordIncorrectException();
			else throw new UserUnavailableException();
		}
	}

	@Override
	public ResponseData updatePassword(String username, String currentPassword, String newPassword) throws PasswordIncorrectException,UserUnavailableException{
		// TODO Auto-generated method stub
		Optional<UserEntity> u = urepo.findByusername(username);
		ResponseData resData = null;
		if(u.isPresent() && u.get().getPassword().equals(currentPassword)){
			UserEntity ue = u.get();
			ue.setPassword(newPassword);
			urepo.save(ue);
			UserAdapter ua = new UserAdapter ();
			
			UserDto udto = ua.userDaoToDto(ue);
			resData = udto;
			return resData;
		} else {
			if(!u.get().getPassword().equals(currentPassword)) throw new PasswordIncorrectException();
			else throw new UserUnavailableException();
		}
	}

	@Override
	public ResponseData updateEmail(String username, String password , String newEmail) throws PasswordIncorrectException,UserUnavailableException{
		// TODO Auto-generated method stub
		Optional<UserEntity> u = urepo.findByusername(username);
		ResponseData resData = null;
		if(u.isPresent() && u.get().getPassword().equals(password)){
			UserEntity ue = u.get();
			ue.setEmail(newEmail);
			urepo.save(ue);
			UserAdapter ua = new UserAdapter ();
			
			UserDto udto = ua.userDaoToDto(ue);
			resData = udto;
			return resData;
		}else {
			if(!u.get().getPassword().equals(password)) throw new PasswordIncorrectException();
			else throw new UserUnavailableException();
		}
	}

//	@Override
//	public ResponseData removeUserRole(String username, String role) throws UserUnavailableException{
//		// TODO Auto-generated method stub
//		Optional<UserEntity> u = urepo.findByusername(username);
//		ResponseData resData = null;
//		if(u.isPresent()) {
//			UserEntity ue = u.get();
//			Set<Roles> newSet = new HashSet<>();
//			u.get().getUserRoles().forEach(roles -> {
//			     if(!role.equals(roles.getRole())){
//			    	 newSet.add(roles);
//			     }
//			});
//			ue.setUserRoles(newSet);
//			urepo.save(ue);
//			UserAdapter ua = new UserAdapter();
//			RoleAdapter ra = new RoleAdapter();
//			UserDto udto = ua.userDaoToDto(ue);
//			resData = udto;
//			return resData;
//		}
//		else {
//			throw new UserUnavailableException();
//		}
//		
//	}

	@Override
	public List<String> getUsernames() throws UserUnavailableException{
		// TODO Auto-generated method stub
		Iterable<UserEntity> itUe = urepo.findAll();
		Optional<Iterable<UserEntity>> opItUe = Optional.of(itUe);
		if(opItUe.isPresent() && opItUe.get()!=null) {
			List<String> ls = new ArrayList<>();
			opItUe.get().forEach(ue -> {
				ls.add(ue.getUsername());
			});
			return ls;
		} else {
			throw new UserUnavailableException();
		}
	} 
	
	public List<String> getAdmins() throws RoleNotFoundException{
		Iterable<UserEntity> itue = urepo.findAllByrole("ADMIN");
	    if(itue!=null && ((Collection<UserEntity>) itue).size()>0) {
	    	List<String> ls = new ArrayList<>();
	    		itue.forEach(user -> {
	    			ls.add(user.getUsername());
	    		});
	    		return ls;
	    	} 
	     else {
	    	throw new RoleNotFoundException();
	  }
   }
	
	
	public List<String> getCaptains() throws RoleNotFoundException{
		Iterable<UserEntity> itue = urepo.findAllByrole("CAPTAIN");
	    if(itue!=null && ((Collection<UserEntity>) itue).size()>0) {
	    	List<String> ls = new ArrayList<>();
	    		itue.forEach(user -> {
	    			ls.add(user.getUsername());
	    		});
	    		return ls;
	    	} 
	     else {
	    	throw new RoleNotFoundException();
	  }
	}
	
	public List<String> getPlayers() throws RoleNotFoundException{
		Iterable<UserEntity> itue = urepo.findAllByrole("PLAYER");
	    if(itue!=null && ((Collection<UserEntity>) itue).size()>0) {
	    	List<String> ls = new ArrayList<>();
	    		itue.forEach(user -> {
	    			ls.add(user.getUsername());
	    		});
	    		return ls;
	    	} 
	     else {
	    	throw new RoleNotFoundException();
	  }
	}

	@Override
	public List<String> getRoles() throws RoleNotFoundException{
		// TODO Auto-generated method stub
		Iterable<Roles> itr = rrepo.findAll();
		if(itr!=null && ((Collection<Roles>) itr).size()>0) {
			List<String> ls = new ArrayList<String>();
			itr.forEach(r ->{
				ls.add(r.getRole());
			});
			return ls;
		} else {
			throw new RoleNotFoundException();
		}
		
	}

	@Override
	public List<String> postRoles(RoleDto rdto) throws RoleFoundException{
		// TODO Auto-generated method stub
		Optional<Roles> opr = rrepo.findByrole(rdto.getRole());
		if(opr.isPresent()) {
			throw new RoleFoundException();
		}
		RoleAdapter ra = new RoleAdapter();
		Roles r = ra.roleDtoToDao(rdto);
		rrepo.save(r);
		Iterable<Roles> itr = rrepo.findAll();
		List<String> ls = new ArrayList<String>();
		itr.forEach(role ->{
			ls.add(role.getRole());
		});
		return ls;
	}

	
	
	
       
}
