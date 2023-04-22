package com.jjang051.ch05.service;

import com.jjang051.ch05.dao.BoardDao;
import com.jjang051.ch05.dto.BoardDto;
import com.jjang051.ch05.dto.Criteria;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {

  @Autowired
  BoardDao boardDao;

  @Override
  public int writeBoard(BoardDto boardDto) {
    boardDto.setBoardGroup(getMaxGroup() + 1);
    boardDto.setBoardLevel(1);
    boardDto.setBoardStep(1);
    log.info("boardGroup===" + boardDto.getBoardGroup());
    int result = boardDao.writeBoard(boardDto);
    return result;
  }

  @Override
  public List<BoardDto> getBoardList(Criteria criteria) {
    List<BoardDto> boardList = boardDao.getBoardList(criteria);
    return boardList;
  }

  @Override
  public BoardDto getBoardOne(int no) {
    // business logic 이 잔득 들어간다.
    updateHit(no);
    BoardDto boardDto = boardDao.getBoardOne(no);
    return boardDto;
  }

  @Override
  public int updateHit(int no) {
    int result = boardDao.updateHit(no);
    return result;
  }

  @Override
  public int getMaxGroup() {
    int max = boardDao.getMaxGroup();
    return max;
  }

  @Override
  public int updateLevel(BoardDto boardDto) {
    int result = boardDao.updateLevel(boardDto);
    return result;
  }

  @Override
  public int replyBoard(BoardDto boardDto) {
    int boardGroup = boardDto.getBoardGroup();
    int boardLevel = boardDto.getBoardLevel();
    int boardStep = boardDto.getBoardStep();

    updateLevel(boardDto);

    boardDto.setBoardGroup(boardGroup);
    boardDto.setBoardLevel(boardLevel + 1);
    boardDto.setBoardStep(boardStep + 1);

    int result = boardDao.replyBoard(boardDto);

    return result;
  }

  @Override
  public int getTotalCount() {
    int result = boardDao.getTotalCount();
    return result;
  }
}
