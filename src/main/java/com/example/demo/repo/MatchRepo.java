package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.match.MatchEntity;

@Repository
public interface MatchRepo extends CrudRepository<MatchEntity, Long>{

	public Optional<MatchEntity> findBymatchName(String matchName);
	
	public Iterable<MatchEntity> findBydate(String date);
}
