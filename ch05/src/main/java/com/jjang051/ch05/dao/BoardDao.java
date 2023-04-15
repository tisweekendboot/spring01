package com.jjang051.ch05.dao;

import com.jjang051.ch05.dto.BoardDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
  int writeBoard(BoardDto boardDto);
  List<BoardDto> getBoardList();
  BoardDto getBoardOne(int no);
  int updateHit(int no);
}
