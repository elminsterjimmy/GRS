package com.elminster.grs.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elminster.grs.web.domain.GameInfoUpdateHistory;

@Repository
public interface GameInfoUpdateHistoryDao extends JpaRepository<GameInfoUpdateHistory, Integer> {

  public GameInfoUpdateHistory findByUserId(int userId);
}
