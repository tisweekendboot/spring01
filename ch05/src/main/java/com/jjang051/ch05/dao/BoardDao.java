package com.jjang051.ch05.dao;

import com.jjang051.ch05.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
  int writeBoard(BoardDto boardDto);
}
