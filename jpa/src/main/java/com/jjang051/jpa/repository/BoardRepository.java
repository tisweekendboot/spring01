package com.jjang051.jpa.repository;

import com.jjang051.jpa.entity.Board02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface BoardRepository extends JpaRepository<Board02, Integer> {
  // 기본적인 crud
  // 필요하면 만들어 쓰면 된다.
}
