package com.jjang051.ch05.service;

import com.jjang051.ch05.dto.BoardDto;
import javax.validation.Valid;

public interface BoardService {
  int writeBoard(BoardDto boardDto);
}
