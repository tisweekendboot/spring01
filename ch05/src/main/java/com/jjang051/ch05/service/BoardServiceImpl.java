package com.jjang051.ch05.service;

import com.jjang051.ch05.dao.BoardDao;
import com.jjang051.ch05.dto.BoardDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

  @Autowired
  BoardDao boardDao;

  @Override
  public int writeBoard(BoardDto boardDto) {
    int result = boardDao.writeBoard(boardDto);
    return result;
  }

  @Override
  public List<BoardDto> getBoardList() {
    List<BoardDto> boardList = boardDao.getBoardList();
    return boardList;
  }

  @Override
  public BoardDto getBoardOne(int no) {
    // business logic 이 잔득 들어간다.
    updateHit(no);
    BoardDto boardDto = boardDao.getBoardOne(no);
    return boardDto;
  }

  @Override
  public int updateHit(int no) {
    int result = boardDao.updateHit(no);
    return result;
  }
}
