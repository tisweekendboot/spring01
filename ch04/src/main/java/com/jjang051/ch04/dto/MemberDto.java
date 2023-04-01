package com.jjang051.ch04.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MemberDto {

  private String userId;
  private String userPw;
  private String userName;
}
