package com.jjang051.jpa.service;

import com.jjang051.jpa.entity.Board;
import com.jjang051.jpa.repository.BoardRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {

  @Autowired
  BoardRepository boardRepository;

  @Override
  public List<Board> getList() {
    List<Board> list = boardRepository.findAll();
    return list;
  }

  @Override
  public void register(Board vo) {
    boardRepository.save(vo);
  }

  @Override
  public Board get(Long idx) {
    Optional<Board> board = boardRepository.findById(idx);
    log.info("board service===", idx);
    return board.get();
  }

  @Override
  public void delete(Long idx) {
    boardRepository.deleteById(idx);
  }

  @Override
  public void update(Board vo) {
    boardRepository.save(vo); // insert, update
  }

  @Override
  public List<Board> findByWriter(String writer) {
    List<Board> boardList = boardRepository.findByWriter(writer);
    return boardList;
  }

  @Override
  public Page<Board> getListPage(Pageable pageable) {
    Page<Board> page = boardRepository.findAll(pageable);

    // List<Board> content = page.getContent();
    // log.info("content===={}", content);
    // long getTotalElements = page.getTotalElements();
    // log.info("getTotalElements===={}", getTotalElements);

    return page;
  }

  @Override
  public Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
    Page<Board> page = boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);
    return page;
  }

  @Override
  public List<Board> findByWriterQuery(String writer) {
    List<Board> boardList = boardRepository.findByWriterQuery(writer);
    return boardList;
  }

  @Override
  public List<Board> findByWriterQueryNative(String writer) {
    List<Board> boardList = boardRepository.findByWriterQueryNative(writer);
    return boardList;
  }

  @Override
  public int deleteWithPassword(Board board) {
    int result = boardRepository.deleteWithPassword(board);
    return result;
  }
}
