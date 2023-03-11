package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Team.TeamEntity;
import com.example.demo.role.Roles;

@Repository
public interface TeamRepo extends CrudRepository<TeamEntity,Long>{
     
	public Optional<TeamEntity> findByteamName(String teamName);
}
