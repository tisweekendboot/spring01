package com.jjang051.jpa.service;

import com.jjang051.jpa.entity.Board;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
  public List<Board> getList(); //전체리스트

  public Page<Board> getListPage(Pageable pageable); //전체리스트

  public void register(Board vo); // 글등록

  public Board get(Long idx); // 상세보기

  public void delete(Long idx); // 삭제

  public int deleteWithPassword(Board board); // 삭제

  public void update(Board vo); // 수정

  public List<Board> findByWriter(String writer);

  Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

  List<Board> findByWriterQuery(String writer);

  List<Board> findByWriterQueryNative(String writer);
}
