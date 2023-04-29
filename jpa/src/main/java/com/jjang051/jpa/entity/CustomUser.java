package com.jjang051.jpa.entity;

import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Data
public class CustomUser extends User {

  // private Member member;

  // public CustomUser(Member member) {
  //   super(
  //     member.getUsername(),
  //     member.getPassword(),
  //     AuthorityUtils.createAuthorityList("ROLE_" + member.getRole().toString())
  //   );
  //   this.member = member;
  // }
  private Member member;

  public CustomUser(Member member) {
    super(
      member.getUsername(),
      member.getPassword(),
      AuthorityUtils.createAuthorityList("ROLE_" + member.getRole().toString())
    );
    this.member = member;
  }
}
