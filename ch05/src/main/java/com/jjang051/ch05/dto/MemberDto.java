package com.jjang051.ch05.dto;

import lombok.Data;

@Data
public class MemberDto {

  private int no;
  private String userId;
  private String userPw;
  private String userName;
  private String userEmail;
  private String userAddr;
  private String regDate;
}
