package com.jjang051.ch02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @Getter
// @Setter
// @ToString
// @AllArgsConstructor
// @NoArgsConstructor

@Data
public class Member {

  private String name;
  private String password;
}
