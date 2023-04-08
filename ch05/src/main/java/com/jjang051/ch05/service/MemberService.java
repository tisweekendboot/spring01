package com.jjang051.ch05.service;

import com.jjang051.ch05.dto.MemberDto;

//정의 만 내리는 사람 부장
public interface MemberService {
  int insertMember(MemberDto memberDto);
  int idCheck(MemberDto memberDto);
}
