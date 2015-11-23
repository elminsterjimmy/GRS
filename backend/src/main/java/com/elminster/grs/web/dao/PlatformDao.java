package com.elminster.grs.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.web.domain.Platform;

@Repository
public interface PlatformDao extends JpaRepository<Platform, Integer>, JpaSpecificationExecutor<Platform> {

  Platform findByPlatform(String platformName);
}
