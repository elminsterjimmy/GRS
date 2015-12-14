package com.elminster.grs.shared.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elminster.grs.shared.db.domain.UserGameMeta;

@Repository
public interface UserGameMetaDao extends JpaRepository<UserGameMeta, Integer> {

}
