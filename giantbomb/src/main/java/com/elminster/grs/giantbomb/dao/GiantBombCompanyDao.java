package com.elminster.grs.giantbomb.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.elminster.grs.giantbomb.ds.GiantBombCompany;

@Repository
public interface GiantBombCompanyDao extends BaseObjectRepository<GiantBombCompany, Integer>, JpaSpecificationExecutor<GiantBombCompany> {

}
