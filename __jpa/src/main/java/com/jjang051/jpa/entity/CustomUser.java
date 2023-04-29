package com.jjang051.jpa.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Data;


//  권한 ad

@Data
public class CustomUser extends User {
  
  private Member03 member;
  
  public CustomUser(Member03 member) {
    super(
      member.getUsername(),
      member.getPassword(),
      AuthorityUtils.createAuthorityList("ROLE_"+member.getRole().toString());
    );
    this.member = member;
  }
}
