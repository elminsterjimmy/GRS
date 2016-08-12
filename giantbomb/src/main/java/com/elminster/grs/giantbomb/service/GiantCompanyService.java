package com.elminster.grs.giantbomb.service;

import java.util.Set;

import com.elminster.grs.giantbomb.ds.GiantBombCompany;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;

public interface GiantCompanyService {

  public Set<GiantBombCompany> saveCompanys(Set<GiantBombCompany> companys);

  public GiantBombCompany saveCompany(GiantBombCompany company);

  public Set<GiantBombCompany> findCompanysByStatus(GiantBombStatus status);

  public void updateStatus(GiantBombCompany company, GiantBombStatus status);

}
