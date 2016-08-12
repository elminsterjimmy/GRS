package com.elminster.grs.giantbomb.ds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="gaintbomb_genre")
public class GiantBombGenre extends BaseObject implements CopyConstructor<GiantBombGenre> {
  
  //@formatter:off
  @Id
  @Column(name="id")
  @GeneratedValue(generator="id_gen")
  @GenericGenerator(name="id_gen", strategy="enhanced-table", 
   parameters = {
     @Parameter(name="table_name", value="gaintbomb_game_seq_gen"),
     @Parameter(name="value_column_name", value="next"), 
     @Parameter(name="segment_column_name",value="seq_name"), 
     @Parameter(name="segment_value", value="genre_seq"),
     @Parameter(name="increment_size", value="1"), 
     @Parameter(name="optimizer", value="pooled-lo") 
   })
  // @formatter:on
  int internalId;

  @Override
  public void fulfill(GiantBombGenre other) {
    super.fulfill(other);
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
}
