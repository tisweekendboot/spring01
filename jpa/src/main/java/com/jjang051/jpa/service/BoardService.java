package com.jjang051.jpa.service;

import com.jjang051.jpa.entity.Board02;
import java.util.List;

public interface BoardService {
  public void write(Board02 board);

  public List<Board02> getList();

  public Board02 getBoard(int no);
}
