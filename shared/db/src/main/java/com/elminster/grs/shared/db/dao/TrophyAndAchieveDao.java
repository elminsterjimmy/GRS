package com.elminster.grs.shared.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elminster.grs.shared.db.domain.TrophyAndAchievement;

@Repository
public interface TrophyAndAchieveDao extends JpaRepository<TrophyAndAchievement, Integer>,
    JpaSpecificationExecutor<TrophyAndAchievement> {

  @Query("SELECT tna FROM TrophyAndAchievement tna WHERE tna.game.id = ?1 and tna.trophyId = ?2")
  public TrophyAndAchievement findByGameIdAndTrophyId(int gameId, String trophyId);
}
