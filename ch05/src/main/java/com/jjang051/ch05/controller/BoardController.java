package com.jjang051.ch05.controller;

import com.jjang051.ch05.dto.BoardDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

  @GetMapping("/write")
  public String write(Model model) {
    model.addAttribute("boardDto", new BoardDto());
    return "/board/write";
  }
}
