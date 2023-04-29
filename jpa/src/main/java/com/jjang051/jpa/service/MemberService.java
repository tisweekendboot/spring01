package com.jjang051.jpa.service;

import com.jjang051.jpa.entity.Member;

public interface MemberService {
  public void register(Member member);

  public void updateWithPassword(Member member);

  public void deleteById(String username);
}
