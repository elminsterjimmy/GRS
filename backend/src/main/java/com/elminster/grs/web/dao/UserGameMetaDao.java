package com.elminster.grs.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elminster.grs.web.domain.UserGameMeta;

@Repository
public interface UserGameMetaDao extends JpaRepository<UserGameMeta, Integer> {

}
