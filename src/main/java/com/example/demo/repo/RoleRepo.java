package com.example.demo.repo;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.role.Roles;

@Repository
public interface RoleRepo extends CrudRepository<Roles,Integer>{
      
	public Optional<Roles> findByrole(String role);
	
	public Iterable<Roles> findAllByrole(String role);
	
}
