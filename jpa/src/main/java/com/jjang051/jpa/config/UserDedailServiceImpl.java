package com.jjang051.jpa.config;

import com.jjang051.jpa.entity.Member03;
import com.jjang051.jpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDedailServiceImpl implements UserDetailsService {

  @Autowired
  MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    Member03 member = memberRepository.findById(username).get();
    if (member == null) {
      throw new UsernameNotFoundException(username + "님 없음");
    }
    return new CustomUser(member);
  }
}
