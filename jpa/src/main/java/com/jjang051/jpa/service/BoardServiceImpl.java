package com.jjang051.jpa.service;

import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

  // dao역할을 한다.
  @Autowired
  BoardRepository boardRepository;

  @Override
  public void write(Board02 board) {
    boardRepository.save(board);
  }
}
