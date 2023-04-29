package com.jjang051.jpa.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Board02 {

  @Id
  @GeneratedValue
  private Integer no;

  @Column(length = 200)
  private String title;

  @Column(length = 2000)
  private String contents;

  @Column(length = 200)
  private String writer;

  @Column(length = 200)
  private String password;

  @Column(
    insertable = false,
    updatable = false,
    columnDefinition = "date default sysdate"
  )
  private Date regDate;

  private int hit;
}
