package com.elminster.grs.shared.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.shared.db.domain.UserGame;
import com.elminster.grs.shared.db.domain.UserTrophyAndAchievement;

@Repository
public interface UserTrophyAndAchieveDao extends JpaRepository<UserTrophyAndAchievement, Integer>,
    JpaSpecificationExecutor<UserTrophyAndAchievement> {

  List<UserTrophyAndAchievement> findByUserGame(UserGame userGame);
}