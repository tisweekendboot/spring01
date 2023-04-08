package com.jjang051.ch05.service;

import com.jjang051.ch05.dao.MemberDao;
import com.jjang051.ch05.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class MemberServiceImpl02 implements MemberService {

  @Autowired
  MemberDao memberDao;

  @Override
  public int insertMember(MemberDto memberDto) {
    int result = memberDao.insertMember(memberDto);
    return result;
  }
}
