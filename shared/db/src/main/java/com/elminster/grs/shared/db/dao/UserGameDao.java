package com.elminster.grs.shared.db.dao;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elminster.grs.shared.db.domain.UserGame;


@Repository
public interface UserGameDao extends JpaRepository<UserGame, Integer>, JpaSpecificationExecutor<UserGame> {

  @Query("SELECT ug FROM UserGame ug WHERE ug.user.userId = $1")
  public List<UserGame> findByUserId(int userId, Sort sort);

  @Query("SELECT ug FROM UserGame ug WHERE ug.user.userId = :userId and (ug.game.psnInternalId = :gid or ug.game.liveInternalId = :gid)")
  public UserGame findByUserIdAndGameInternalId(@Param("userId") int userId,
      @Param("gid") String gameInternalId);
}
