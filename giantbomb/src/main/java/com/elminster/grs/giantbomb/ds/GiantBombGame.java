package com.elminster.grs.giantbomb.ds;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.elminster.common.util.CollectionUtil;

@Entity
@Table(name="gaintbomb_game")
public class GiantBombGame extends BaseObject implements CopyConstructor<GiantBombGame> {
  
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
  
  @Column
  String gbApiUrl;
  
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable=false, length=1)
  GiantBombGameStatus status = GiantBombGameStatus.BASIC_INFO_CRAWLED;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "gaintbomb_game_platform",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "platform_id", referencedColumnName = "id") 
  })
  Set<GiantBombPlatform> platforms;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "gaintbomb_game_extra_image",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "image_id", referencedColumnName = "id") 
  })
  Set<GiantBombImage> images;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "gaintbomb_game_video",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "video_id", referencedColumnName = "id") 
  })
  Set<GiantBombVideo> videos;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "gaintbomb_game_developer",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "developer_id", referencedColumnName = "id") 
  })
  Set<GiantBombCompany> developers;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "gaintbomb_game_genre",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "genre_id", referencedColumnName = "id") 
  })
  Set<GiantBombGenre> genres;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "gaintbomb_game_publisher",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "publisher_id", referencedColumnName = "id") 
  })
  Set<GiantBombCompany> publishers;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "gaintbomb_game_theme",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "theme_id", referencedColumnName = "id") 
  })
  Set<GiantBombTheme> themes;

  @Override
  public void fulfill(GiantBombGame other) {
    if (null != other) {
      super.fulfill(other);
      this.aliases = other.aliases;
      this.gbApiUrl = other.gbApiUrl;
      this.status = other.status;
      if (CollectionUtil.isNotEmpty(platforms)) {
        this.platforms = other.platforms;
      }
      if (CollectionUtil.isNotEmpty(images)) {
        this.images = other.images;
      }
      if (CollectionUtil.isNotEmpty(developers)) {
        this.developers = other.developers;
      }
      if (CollectionUtil.isNotEmpty(genres)) {
        this.genres = other.genres;
      }
      if (CollectionUtil.isNotEmpty(publishers)) {
        this.publishers = other.publishers;
      }
      if (CollectionUtil.isNotEmpty(themes)) {
        this.themes = other.themes;
      }
      if (CollectionUtil.isNotEmpty(videos)) {
        this.videos = other.videos;
      }
    }
  }
  
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
  @XmlElement(name="platform")
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
  @XmlElementWrapper(name="images")
  @XmlElement(name="image")
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
  @XmlElementWrapper(name="videos")
  @XmlElement(name="video")
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
  @XmlElementWrapper(name="developers")
  @XmlElement(name="company")
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
  @XmlElementWrapper(name="genres")
  @XmlElement(name="genre")
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
  @XmlElementWrapper(name="publishers")
  @XmlElement(name="publisher")
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
  @XmlElementWrapper(name="themes")
  @XmlElement(name="theme")
  public void setThemes(Set<GiantBombTheme> themes) {
    this.themes = themes;
  }

  /**
   * @return the gbApiUrl
   */
  public String getGbApiUrl() {
    return gbApiUrl;
  }

  /**
   * @param gbApiUrl the gbApiUrl to set
   */
  @XmlElement(name = "api_detail_url")
  public void setGbApiUrl(String gbApiUrl) {
    this.gbApiUrl = gbApiUrl;
  }

  /**
   * @return the status
   */
  public GiantBombGameStatus getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  
  public void setStatus(GiantBombGameStatus status) {
    this.status = status;
  }
  
  public enum GiantBombGameStatus {
    BASIC_INFO_CRAWLED,
    DETAIL_INFO_CRAWLED,
    DETAIL_INFO_UPDATED,
  }
}
