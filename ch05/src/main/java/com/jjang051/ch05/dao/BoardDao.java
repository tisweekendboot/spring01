package com.jjang051.ch05.dao;

import com.jjang051.ch05.dto.BoardDto;
import com.jjang051.ch05.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
  int writeBoard(BoardDto boardDto);
  List<BoardDto> getBoardList(Criteria criteria);
  BoardDto getBoardOne(int no);
  int updateHit(int no);
  int getMaxGroup();
  int updateLevel(BoardDto boardDto);
  int replyBoard(BoardDto boardDto);
  int getTotalCount();
}
