package com.jjang051.ch05.controller;

import com.jjang051.ch05.dto.BoardDto;
import com.jjang051.ch05.dto.Criteria;
import com.jjang051.ch05.service.BoardService;
import com.jjang051.ch05.utils.PageMaker;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
public class BoardController {

  @Autowired
  BoardService boardService;

  @Autowired
  PageMaker pageMaker;

  @GetMapping("/write")
  public String write(Model model) {
    model.addAttribute("boardDto", new BoardDto());
    return "/board/write";
  }

  @PostMapping("/write")
  public String writeProcess(@Valid BoardDto boardDto, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("boardDto", boardDto);
      return "/board/write";
    }
    int result = boardService.writeBoard(boardDto);
    return "redirect:/board/list";
  }

  @GetMapping("/list")
  public String list(Model model, Criteria criteria) {
    List<BoardDto> boardList = boardService.getBoardList(criteria);
    //Criteria customeCriteria = new Criteria(1, 8);
    pageMaker.setCriteria(criteria);
    pageMaker.setTotalCount(boardService.getTotalCount());
    model.addAttribute("boardList", boardList);
    model.addAttribute("pageMaker", pageMaker);
    log.info("pageMaker===={}", pageMaker.toString());
    return "/board/list";
  }

  @GetMapping("/view")
  public String view(Model model, int no) {
    BoardDto boardDto = boardService.getBoardOne(no);
    model.addAttribute("boardDto", boardDto);
    return "/board/view";
  }

  @GetMapping("/reply")
  public String reply(Model model) {
    model.addAttribute("boardDto", new BoardDto());
    return "/board/reply";
  }

  @PostMapping("/reply")
  public String replyProcess(@Valid BoardDto boardDto, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("boardDto", boardDto);
      return "/board/write";
    }
    int result = boardService.replyBoard(boardDto);
    return "redirect:/board/list";
  }

  @GetMapping("/delete")
  public String delete(Model model) {
    model.addAttribute("boardDto", new BoardDto());
    return "/board/delete";
  }

  @PostMapping("/delete")
  public String deleteProcess(BoardDto boardDto, Model model, RedirectAttributes redirectAttributes) {
    int result = boardService.deleteBoard(boardDto);
    if (result > 0) {
      return "redirect:/";
    }
    redirectAttributes.addFlashAttribute("isState", "show");
    redirectAttributes.addFlashAttribute("msg", "비밀번호를 확인해 주세요.");
    //model.addAttribute("boardDto", new BoardDto());
    return "redirect:/board/delete?no=" + boardDto.getNo();
  }
}
