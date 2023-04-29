package com.jjang051.jpa.repository;

import com.jjang051.jpa.entity.Board02;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@Repository
public interface BoardRepository extends JpaRepository<Board02, Integer> {
  // 기본적인 crud
  // 필요하면 만들어 쓰면 된다.

  /*
  @Query(
    value = "delete from board02 where no = :no and password = :password",
    nativeQuery = true
  )
  public int deleteWithPassword(@Param("no") int no, @Param("password") String password);
  */

  public List<Board02> findByWriter(String writer);

  public List<Board02> findByTitleContainingOrContentsContaining(
    String title,
    String contents
  );

  @Query(
    value = "delete from board02 where no = :#{#paramBoard.no} and password = :#{#paramBoard.password}",
    nativeQuery = true
  )
  @Modifying(clearAutomatically = true)
  @Transactional
  public int deleteWithPassword(@Param("paramBoard") Board02 board);
}
