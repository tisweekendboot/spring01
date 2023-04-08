package com.jjang051.ch05.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberDto {

  private int no;

  @NotBlank(message = "필수 입력 사항입니다.")
  @Size(min = 5, max = 15, message = "좀 길게 적어 주세요")
  private String userId;

  @NotBlank
  @Size(min = 5, max = 15)
  private String userPw;

  @NotBlank
  @Size(min = 5, max = 15)
  private String userName;

  @Pattern(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")
  private String userEmail;

  private String userAddr;
  private String regDate;
}
