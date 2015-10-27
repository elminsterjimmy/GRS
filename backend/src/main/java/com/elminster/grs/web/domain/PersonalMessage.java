package com.elminster.grs.web.domain;

import java.util.Date;

public class PersonalMessage {

  private int id;
  private int fromId;
  private int toId;
  private String message;
  private Date createDate;
  private boolean read;
  private PersonalMessage parent;
}
