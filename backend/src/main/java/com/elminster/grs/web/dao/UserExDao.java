package com.elminster.grs.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elminster.grs.web.domain.UserEx;

@Repository
public interface UserExDao extends JpaRepository<UserEx, Integer> {

}