package com.jjang051.ch05.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class BoardDto {

  private int no; // 고유 번호

  @NotEmpty(message = "필수 입력사항입니다.")
  private String userName;

  @NotEmpty(message = "필수 입력사항입니다.")
  private String title;

  @NotEmpty(message = "필수 입력사항입니다.")
  @Size(min = 10, max = 3000, message = "10글자 이상 쓰셔야 합니다.")
  private String contents;

  private int group;
  private int level;
  private int step;

  private String regDate;
  private int hit;
  private int available; // 1. 글이 남아 있는 경우, 0. 글이 삭제된 경우 글이 삭제되었습니다.

  private int num; // 순서 정하기...

  @NotEmpty(message = "필수 입력사항입니다.")
  private String userPw;
}
