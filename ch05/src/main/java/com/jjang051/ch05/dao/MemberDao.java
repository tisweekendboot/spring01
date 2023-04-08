package com.jjang051.ch05.dao;

import com.jjang051.ch05.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
  int insertMember(MemberDto memberDto);
  int idCheck(MemberDto memberDto);
}
