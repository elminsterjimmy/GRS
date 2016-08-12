package com.elminster.grs.giantbomb.service;

public interface InternalFS {
  
  public static final String CRAWLED_FOLDER = "crawled/";
  public static final String UPDATED_FOLDER = "updated/";
  
  public static final String GAMES_FOLDER = "games/";
  public static final String INDIVIDUAL_GAME_FOLDER = "individual/";
  
  public static final String PS3_FOLDER = "ps3/";
  public static final String PS4_FOLDER = "ps4/";
  
  public static final String CRAWLED_PS3_GAME_LIST_FOLDER = CRAWLED_FOLDER + GAMES_FOLDER + PS3_FOLDER;
  public static final String CRAWLED_PS4_GAME_LIST_FOLDER = CRAWLED_FOLDER + GAMES_FOLDER + PS4_FOLDER;
  
  

  public static final String UPDATED_PS3_GAME_LIST_FOLDER = UPDATED_FOLDER + GAMES_FOLDER + PS3_FOLDER;
  public static final String UPDATED_PS4_GAME_LIST_FOLDER = UPDATED_FOLDER + GAMES_FOLDER + PS4_FOLDER;

  public static final String CRAWLED_INDIVIDUAL_GAME_FOLDER = CRAWLED_FOLDER + INDIVIDUAL_GAME_FOLDER;
  public static final String UPDATED_INDIVIDUAL_GAME_FOLDER = UPDATED_FOLDER + INDIVIDUAL_GAME_FOLDER;
}
