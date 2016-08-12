package com.elminster.grs.giantbomb.service;

import java.util.Set;

import com.elminster.grs.giantbomb.ds.GiantBombGame;
import com.elminster.grs.giantbomb.ds.GiantBombStatus;

/**
 * The Service to store the game information that crawled from gaintgame into the database.
 * 
 * @author jgu
 * @version 1.0
 */
public interface GiantGameService {

  public GiantBombGame saveGame(GiantBombGame game);
  
  //// ==================================================================  /////
  public Set<GiantBombGame> findGamesByStatus(GiantBombStatus status);

  public void updateStatus(GiantBombGame game, GiantBombStatus status);
}
