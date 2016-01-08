package com.elminster.grs.shared.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.elminster.grs.shared.db.domain.Location;

public interface LocationDao extends JpaRepository<Location, Integer>, JpaSpecificationExecutor<Location> {

}
