package com.jjang051.jpa.config;

import com.jjang051.jpa.entity.CustomUser;
import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.repository.MemberRepository;
import javax.naming.NameNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

  // @Autowired
  // private MemberRepository memberRepository;

  // @Override
  // public UserDetails loadUserByUsername(String username)
  //   throws UsernameNotFoundException {
  //   Member member = memberRepository.findById(username).get();
  //   if (member == null) {
  //     throw new UsernameNotFoundException(username + " 없음");
  //   }
  //   return new CustomUser(member); // User(3가지 권한정보)+Member(다른 회원정보)
  // }

  @Autowired
  MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("로그인을 하면 여기를 탄다.");
    Member member = memberRepository.findById(username).get();
    if (member == null) {
      throw new UsernameNotFoundException(username + " 없음");
    }
    return new CustomUser(member);
  }
}
