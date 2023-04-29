package com.jjang051.jpa.controller;

import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.service.BoardService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
public class BoardController {

  @Autowired
  BoardService boardService;

  @GetMapping("/write")
  public String write(Model model) {
    model.addAttribute("board", new Board02());
    return "/board/write";
  }

  @PostMapping("/write")
  public String write(Board02 board, Model model) {
    boardService.write(board);
    return "redirect:/";
  }

  @GetMapping("/list")
  public String list(Model model) {
    List<Board02> boardList = boardService.getList();
    model.addAttribute("boardList", boardList);
    return "/board/list";
  }

  @GetMapping("/view")
  public String view(Model model, int no) {
    Board02 board = boardService.getBoard(no);
    model.addAttribute("board", board);
    return "/board/view";
  }

  @GetMapping("/delete")
  public String delete(Model model, int no) {
    model.addAttribute("board", new Board02());
    return "/board/delete";
  }

  @PostMapping("/delete")
  public String deleteProcess(Model model, Board02 board) {
    int result = boardService.deleteWithPassword(board);
    log.info("result====={}", result);
    if (result > 0) {
      return "redirect:/board/list";
    }
    return "redirect:/board/delete?no=" + board.getNo();
  }
}
