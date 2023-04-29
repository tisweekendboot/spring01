package com.jjang051.jpa.service;

import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.repository.BoardRepository;
import java.util.List;
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
    // insert, update
  }

  @Override
  public List<Board02> getList() {
    return boardRepository.findAll();
  }

  @Override
  public Board02 getBoard(int no) {
    return boardRepository.findById(no).get();
  }

  @Override
  public int deleteWithPassword(Board02 board) {
    return boardRepository.deleteWithPassword(board);
  }

  @Override
  public List<Board02> findByWriter(String writer) {
    return boardRepository.findByWriter(writer);
  }

  @Override
  public List<Board02> searchTitle(String search) {
    return boardRepository.findByTitleContainingOrContentsContaining(
      search,
      search
    );
  }
}
