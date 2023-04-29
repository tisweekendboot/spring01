package com.jjang051.jpa.repository;

import com.jjang051.jpa.entity.Board;
import com.jjang051.jpa.entity.Member;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository // 생략                      (CRUD Method)
public interface BoardRepository extends JpaRepository<Board, Long> {
  // public List<Board> findAll();
  // -> select * from Board
  // public Board findById(Long idx);
  //-> select * from Board where idx=#{idx}
  // 쿼리메서드
  // public Board findByWriter(String writer);
  // ->select * from Board where writer=#{writer}
  public List<Board> findByWriter(String writer);

  public Page<Board> findAll(Pageable pageable);

  Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

  @Query("select b from Board b where b.writer like %?1%")
  public List<Board> findByWriterQuery(String writer);

  @Query(value = "select * from Board b where b.writer like %?1%", nativeQuery = true)
  public List<Board> findByWriterQueryNative(String writer);

  @Query(value = "delete from Board where idx = :#{#paramBoard.idx} and password = :#{#paramBoard.password}", nativeQuery = true)
  @Modifying
  @Transactional
  public int deleteWithPassword(@Param("paramBoard") Board board);
}
