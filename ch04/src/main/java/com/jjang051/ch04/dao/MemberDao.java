package com.jjang051.ch04.dao;

import com.jjang051.ch04.dto.MemberDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
  int insertMember(MemberDto memberDto);
  List<MemberDto> getAllMember();
  MemberDto getOneMember(String userId);
}
