package com.jjang051.ch05.service;

import com.jjang051.ch05.dao.MemberDao;
import com.jjang051.ch05.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  MemberDao memberDao;

  @Override
  public int insertMember(MemberDto memberDto) {
    int result = memberDao.insertMember(memberDto);
    return result;
  }

  @Override
  public int idCheck(MemberDto memberDto) {
    int result = memberDao.idCheck(memberDto);
    return result;
  }

  @Override
  public MemberDto loginMember(MemberDto memberDto) {
    MemberDto loginMember = memberDao.loginMember(memberDto);
    return loginMember;
  }
}
