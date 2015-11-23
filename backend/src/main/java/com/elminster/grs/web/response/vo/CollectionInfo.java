package com.elminster.grs.web.response.vo;

public class CollectionInfo {
  
  private int id;

  private String title;
  
  private String cover;
  
  private String platform;
  
  private Trophies trophies;
  
  private int progress;
  
  private boolean favorite;
  
  private int rating;
  
  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the cover
   */
  public String getCover() {
    return cover;
  }

  /**
   * @param cover the cover to set
   */
  public void setCover(String cover) {
    this.cover = cover;
  }

  /**
   * @return the platform
   */
  public String getPlatform() {
    return platform;
  }

  /**
   * @param platform the platform to set
   */
  public void setPlatform(String platform) {
    this.platform = platform;
  }

  /**
   * @return the trophies
   */
  public Trophies getTrophies() {
    return trophies;
  }

  /**
   * @param trophies the trophies to set
   */
  public void setTrophies(Trophies trophies) {
    this.trophies = trophies;
  }

  /**
   * @return the progress
   */
  public int getProgress() {
    return progress;
  }

  /**
   * @param progress the progress to set
   */
  public void setProgress(int progress) {
    this.progress = progress;
  }

  /**
   * @return the favorite
   */
  public boolean isFavorite() {
    return favorite;
  }

  /**
   * @param favorite the favorite to set
   */
  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  /**
   * @return the rating
   */
  public int getRating() {
    return rating;
  }

  /**
   * @param rating the rating to set
   */
  public void setRating(int rating) {
    this.rating = rating;
  }
}
