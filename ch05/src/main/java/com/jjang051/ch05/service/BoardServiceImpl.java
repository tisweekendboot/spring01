package com.jjang051.ch05.service;

import com.jjang051.ch05.dao.BoardDao;
import com.jjang051.ch05.dto.BoardDto;
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
}
