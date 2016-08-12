package com.elminster.grs.giantbomb.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.grs.giantbomb.dao.GiantBombCompanyDao;
import com.elminster.grs.giantbomb.ds.GiantBombCompany;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;
import com.elminster.grs.giantbomb.service.GiantCompanyService;

@Service
public class GiantCompanyServiceImpl implements GiantCompanyService {
  
  @Autowired
  GiantBombCompanyDao CompanyDao;
  
  @Autowired
  private EntityManager em;

  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public Set<GiantBombCompany> saveCompanys(final Set<GiantBombCompany> Companys) {
    Set<GiantBombCompany> managed = null;
    if (null != Companys) {
      managed = new HashSet<>();
      for (GiantBombCompany pl : Companys) {
        managed.add(saveCompany(pl));
      }
    }
    return managed;
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  @Override
  public GiantBombCompany saveCompany(final GiantBombCompany Company) {
    GiantBombCompany managed = null;
    if (null != Company) {
      GiantBombCompany exist = CompanyDao.findByGamebombId(Company.getGamebombId());
      if (null != exist) {
        managed = updateCompany(exist, Company);
      } else {
        managed = persistCompany(Company);
      }
    }
    return managed;
  }
  
  private GiantBombCompany persistCompany(final GiantBombCompany Company) {
    em.persist(Company);
    return Company;
  }

  private GiantBombCompany updateCompany(final GiantBombCompany exist, final GiantBombCompany Company) {
    exist.fulfill(Company);
    return em.merge(exist);
  }

  @Override
  public Set<GiantBombCompany> findCompanysByStatus(GiantBombStatus status) {
    return CompanyDao.findByStatus(status);
  }

  @Override
  public void updateStatus(GiantBombCompany Company, GiantBombStatus status) {
    Company.setStatus(status);
    CompanyDao.save(Company);
  }
}
