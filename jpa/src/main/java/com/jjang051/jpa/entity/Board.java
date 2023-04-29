package com.jjang051.jpa.entity;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import lombok.Data;

@Entity // DataBase의 Table
@Data
public class Board { // VO<--ORM-->Table

  @Id // PK
  @GeneratedValue // 자동증가
  private Long idx; // 자동증가

  private String title;

  @Column(length = 2000)
  private String content;

  //@Column(updatable = false)
  @Column(length = 200)
  private String writer;

  //@Column(updatable = false)
  @Column(length = 200)
  private String password;

  @Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
  private Date indate; // now()

  private LocalDateTime createDate;

  @PrePersist
  public void createDate() {
    this.createDate = LocalDateTime.now();
  }

  @Column(insertable = false, updatable = false, columnDefinition = "int default 0")
  private int count;
}
