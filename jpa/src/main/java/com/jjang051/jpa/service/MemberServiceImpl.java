package com.jjang051.jpa.service;

import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  @Override
  public void register(Member member) {
    memberRepository.save(member);
  }

  @Override
  public void updateWithPassword(Member member) {
    memberRepository.updateWithPassword(member);
  }

  @Override
  public void deleteById(String username) {
    memberRepository.deleteById(username);
  }
}
