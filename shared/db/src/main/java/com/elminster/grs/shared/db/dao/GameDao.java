package com.elminster.grs.shared.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.shared.db.domain.Game;

@Repository
public interface GameDao extends JpaRepository<Game, Integer>, JpaSpecificationExecutor<Game> {

  public Game findByInternalId(String internalId);
}
