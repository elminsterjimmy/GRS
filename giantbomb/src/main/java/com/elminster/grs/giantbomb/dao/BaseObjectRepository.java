package com.elminster.grs.giantbomb.dao;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.elminster.grs.giantbomb.ds.BaseObject;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;

@NoRepositoryBean
public interface BaseObjectRepository<T extends BaseObject, ID extends Serializable> extends JpaRepository<T, ID> {
 
    Set<T> findByStatus(GiantBombStatus status);
    
    T findByGamebombId(int gbId);
}