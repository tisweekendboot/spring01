package com.jjang051.jpa.controller;

import com.jjang051.jpa.entity.Board;
import com.jjang051.jpa.service.BoardService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
public class BoardController {

  @Autowired
  BoardService boardService;

  @RequestMapping("/list")
  public String list(Model model) {
    List<Board> boardList = boardService.getList();
    model.addAttribute("boardList", boardList); // ${list}
    return "/board/list"; // /WEB-INF/board/list.jsp
  }

  @GetMapping("/write")
  public String register(Model model) {
    model.addAttribute("board", new Board());
    return "/board/write"; // /WEB-INF/board/register.jsp
  }

  @PostMapping("/write")
  public String register(@Valid Board board, BindingResult bindingResult, Model model) {
    boardService.register(board);
    return "redirect:/board/list02";
  }

  @GetMapping("/view")
  //@ResponseBody
  public Board view(Long idx) {
    Board board = boardService.get(idx);
    log.info("board====", idx);
    return board;
  }

  @GetMapping("/delete")
  public String delete(Long idx, Model model) {
    model.addAttribute("board", new Board());
    return "board/delete";
  }

  // @PostMapping("/delete")
  // public String deleteProcess(Long idx) {
  //   boardService.delete(idx); // 삭제성공
  //   return "redirect:/board/list";
  // }

  @PostMapping("/delete")
  public String deleteProcess(Board board, RedirectAttributes redirectAttributes) {
    log.info("controller===={}", board);
    int result = boardService.deleteWithPassword(board); // 삭제성공
    log.info("result===={}", result);
    if (result <= 0) {
      redirectAttributes.addFlashAttribute("isState", "show");
      redirectAttributes.addFlashAttribute("msg", "비밀번호를 확인해 주세요.");
      return "redirect:/board/delete?idx=" + board.getIdx();
    }
    redirectAttributes.addFlashAttribute("isState", "show");
    redirectAttributes.addFlashAttribute("msg", "삭제되었습니다.");
    return "redirect:/board/list";
  }

  @PostMapping("/modify")
  public String modify(Board vo) {
    boardService.update(vo);
    return "redirect:/board/list";
  }

  @GetMapping("/writer")
  @ResponseBody
  public List<Board> writer() {
    List<Board> boardList = boardService.findByWriter("정형돈");
    return boardList;
  }

  @RequestMapping("/list02")
  public String list02(Model model, @PageableDefault(size = 2, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable) {
    // PageRequest pageRequest = PageRequest.of(
    //   0,
    //   3,
    //   Sort.by(Sort.Direction.DESC, "idx")
    // );
    Page<Board> page = boardService.getListPage(pageable);
    long totalCount = page.getTotalElements();
    long totalPage = page.getTotalPages();
    List<Board> boardList = page.getContent();

    int startPage = Math.max(1, page.getPageable().getPageNumber() - 4);
    int endPage = Math.min(page.getTotalPages(), page.getPageable().getPageNumber() + 4);
    int currentPage = page.getPageable().getPageNumber();
    log.info("currentPage==={}", currentPage);

    model.addAttribute("boardList", boardList); // ${list}
    model.addAttribute("totalCount", totalCount);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    model.addAttribute("currentPage", currentPage);

    return "/board/list";
  }

  @RequestMapping("/list03")
  public String list03(Model model, @PageableDefault(size = 2, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {
    // PageRequest pageRequest = PageRequest.of(
    //   0,
    //   3,
    //   Sort.by(Sort.Direction.DESC, "idx")
    // );
    Page<Board> page = boardService.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
    long totalCount = page.getTotalElements();
    long totalPage = page.getTotalPages();
    List<Board> boardList = page.getContent();
    int startPage = Math.max(1, page.getPageable().getPageNumber() - 4);
    int endPage = Math.min(page.getTotalPages(), page.getPageable().getPageNumber() + 4);
    int currentPage = page.getPageable().getPageNumber();
    log.info("currentPage==={}", currentPage);

    model.addAttribute("boardList", boardList); // ${list}
    model.addAttribute("totalCount", totalCount);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    model.addAttribute("currentPage", currentPage);

    return "/board/list";
  }

  @GetMapping("/list04")
  public ResponseEntity<?> list04(String writer) {
    List<Board> boardList = boardService.findByWriterQuery(writer);
    log.info(boardList);
    return ResponseEntity.status(HttpStatus.OK).body(boardList);
  }

  @GetMapping("/list05")
  public ResponseEntity<?> list05(String writer) {
    List<Board> boardList = boardService.findByWriterQueryNative(writer);
    log.info(boardList);
    return ResponseEntity.status(HttpStatus.OK).body(boardList);
  }
}
