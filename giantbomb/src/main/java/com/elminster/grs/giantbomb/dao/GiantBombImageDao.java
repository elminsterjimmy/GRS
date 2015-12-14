package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.GiantBombImage;

@Repository
public interface GiantBombImageDao extends JpaRepository<GiantBombImage, Integer>, JpaSpecificationExecutor<GiantBombImage> {

}
