package com.example.demo.repo;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.UserEntity;

@Repository
public interface UserRepo extends CrudRepository<UserEntity,Long> {
       
	public Optional<UserEntity> findByusername(String username);
	
	public void deleteByusername(String username);
	
	public Iterable<UserEntity> findAllByrole(String role);
	
}
