package com.jjang051.ch05.service;

import com.jjang051.ch05.dto.BoardDto;
import java.util.List;
import javax.validation.Valid;

public interface BoardService {
  int writeBoard(BoardDto boardDto);
  List<BoardDto> getBoardList();
  BoardDto getBoardOne(int no);
  int updateHit(int no);
  int getMaxGroup();
  int replyBoard(BoardDto boardDto);
  int updateLevel(BoardDto boardDto);
}
