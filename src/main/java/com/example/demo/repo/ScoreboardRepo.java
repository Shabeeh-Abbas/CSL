package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Scoreboard.Scoreboard;
import com.example.demo.match.MatchEntity;

@Repository
public interface ScoreboardRepo extends CrudRepository<Scoreboard, Integer>{
      
	  public Optional<Scoreboard> findBymatch(String match);
}
