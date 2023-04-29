package com.jjang051.jpa.controller;

import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
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
}
