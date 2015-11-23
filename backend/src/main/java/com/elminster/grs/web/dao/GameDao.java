package com.elminster.grs.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.web.domain.Game;

@Repository
public interface GameDao extends JpaRepository<Game, Integer>, JpaSpecificationExecutor<Game> {

  public Game findByInternalId(String internalId);
}
