package com.jjang051.ch05.controller;

import com.jjang051.ch05.dto.BoardDto;
import com.jjang051.ch05.service.BoardService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    model.addAttribute("boardDto", new BoardDto());
    return "/board/write";
  }

  @PostMapping("/write")
  public String writeProcess(
    @Valid BoardDto boardDto,
    BindingResult bindingResult,
    Model model
  ) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("boardDto", boardDto);
      return "/board/write";
    }
    int result = boardService.writeBoard(boardDto);
    return "redirect:/board/list";
  }

  @GetMapping("/list")
  public String list(Model model) {
    List<BoardDto> boardList = boardService.getBoardList();
    model.addAttribute("boardList", boardList);
    return "/board/list";
  }

  @GetMapping("/view")
  public String view(Model model, int no) {
    BoardDto boardDto = boardService.getBoardOne(no);
    model.addAttribute("boardDto", boardDto);
    return "/board/view";
  }
}
