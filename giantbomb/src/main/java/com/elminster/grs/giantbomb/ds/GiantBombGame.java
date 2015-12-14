package com.elminster.grs.giantbomb.ds;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="gaintbomb_game")
public class GiantBombGame extends BaseObject {
  
  //@formatter:off
  @Id
  @Column(name="id")
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="gaintbomb_game_seq_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="seq_name"), 
     @Parameter(name="segment_value", value="game_seq"),
     @Parameter(name="increment_size", value="1"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  int internalId;

  @Column
  String aliases;
  
  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)    
  @JoinTable(name = "gaintbomb_game_platform",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "platform_id", referencedColumnName = "id")     
  })
  Set<GiantBombPlatform> platforms;
  
  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)    
  @JoinTable(name = "gaintbomb_game_extra_image",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "image_id", referencedColumnName = "id")     
  })
  Set<GiantBombImage> images;
  
  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)    
  @JoinTable(name = "gaintbomb_game_video",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "video_id", referencedColumnName = "id")     
  })
  Set<GiantBombVideo> videos;
  
  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)    
  @JoinTable(name = "gaintbomb_game_developer",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "developer_id", referencedColumnName = "id")     
  })
  Set<GiantBombCompany> developers;
  
  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)    
  @JoinTable(name = "gaintbomb_game_developer",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "developer_id", referencedColumnName = "id")     
  })
  Set<GiantBombGenre> genres;
  
  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)    
  @JoinTable(name = "gaintbomb_game_publisher",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "publisher_id", referencedColumnName = "id")     
  })
  Set<GiantBombCompany> publishers;
  
  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)    
  @JoinTable(name = "gaintbomb_game_theme",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "theme_id", referencedColumnName = "id")     
  })
  Set<GiantBombTheme> themes;

  /**
   * @return the internalId
   */
  public int getInternalId() {
    return internalId;
  }

  /**
   * @param internalId the internalId to set
   */
  public void setInternalId(int internalId) {
    this.internalId = internalId;
  }

  /**
   * @return the aliases
   */
  public String getAliases() {
    return aliases;
  }

  /**
   * @param aliases the aliases to set
   */
  public void setAliases(String aliases) {
    this.aliases = aliases;
  }

  /**
   * @return the platforms
   */
  public Set<GiantBombPlatform> getPlatforms() {
    return platforms;
  }

  /**
   * @param platforms the platforms to set
   */
  @XmlElementWrapper(name="platforms")
  @XmlElement(name = "platform")
  public void setPlatforms(Set<GiantBombPlatform> platforms) {
    this.platforms = platforms;
  }

  /**
   * @return the images
   */
  public Set<GiantBombImage> getImages() {
    return images;
  }

  /**
   * @param images the images to set
   */
  public void setImages(Set<GiantBombImage> images) {
    this.images = images;
  }

  /**
   * @return the videos
   */
  public Set<GiantBombVideo> getVideos() {
    return videos;
  }

  /**
   * @param videos the videos to set
   */
  public void setVideos(Set<GiantBombVideo> videos) {
    this.videos = videos;
  }

  /**
   * @return the developers
   */
  public Set<GiantBombCompany> getDevelopers() {
    return developers;
  }

  /**
   * @param developers the developers to set
   */
  public void setDevelopers(Set<GiantBombCompany> developers) {
    this.developers = developers;
  }

  /**
   * @return the genres
   */
  public Set<GiantBombGenre> getGenres() {
    return genres;
  }

  /**
   * @param genres the genres to set
   */
  public void setGenres(Set<GiantBombGenre> genres) {
    this.genres = genres;
  }

  /**
   * @return the publishers
   */
  public Set<GiantBombCompany> getPublishers() {
    return publishers;
  }

  /**
   * @param publishers the publishers to set
   */
  public void setPublishers(Set<GiantBombCompany> publishers) {
    this.publishers = publishers;
  }

  /**
   * @return the themes
   */
  public Set<GiantBombTheme> getThemes() {
    return themes;
  }

  /**
   * @param themes the themes to set
   */
  public void setThemes(Set<GiantBombTheme> themes) {
    this.themes = themes;
  }
}
