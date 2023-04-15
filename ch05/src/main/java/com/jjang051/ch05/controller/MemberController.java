package com.jjang051.ch05.controller;

import com.jjang051.ch05.dto.MemberDto;
import com.jjang051.ch05.service.MemberService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

  @Autowired
  MemberService memberService;

  @GetMapping("/join")
  public String join(Model model) {
    model.addAttribute("memberDto", new MemberDto());
    return "/member/join";
  }

  @PostMapping("/join")
  //@ResponseBody
  public String joinProcess(
    @Valid MemberDto memberDto,
    BindingResult bindingResult,
    Model model
  ) {
    log.info("============memberDto===============");
    log.info(memberDto);
    if (bindingResult.hasErrors()) {
      model.addAttribute("memberDto", memberDto);
      return "/member/join";
    }
    int result = memberService.insertMember(memberDto);
    return "redirect:/";
  }

  /*
  @PostMapping("/idCheck")
  @ResponseBody
  public Map<String, Integer> idCheck(MemberDto memberDto) {
    log.info(memberDto);
    int result = memberService.idCheck(memberDto);
    log.info("result====" + result);
    Map<String, Integer> resultMap = new HashMap<>();
    resultMap.put("result", result);
    return resultMap;
  }
  */
  @PostMapping("/idCheck")
  public ResponseEntity<?> idCheck(MemberDto memberDto) {
    log.info(memberDto);
    int result = memberService.idCheck(memberDto);
    log.info("result====" + result);
    Map<String, Integer> resultMap = new HashMap<>();
    resultMap.put("result", result);
    return ResponseEntity.status(HttpStatus.OK).body(resultMap);
  }

  @GetMapping("/login")
  public String login() {
    return "/member/login";
  }

  @PostMapping("/login")
  public String loginProcess(MemberDto memberDto, HttpSession session) {
    MemberDto loggedMember = memberService.loginMember(memberDto);
    if (loggedMember != null) {
      session.setAttribute("loggedMember", loggedMember);
      log.info(loggedMember.toString());
      return "redirect:/";
    }
    //model.addAttribute("loggedMember", loggedMember);
    return "redirect:/member/login";
  }

  @GetMapping("/logout")
  public String logOut(HttpSession session) {
    session.invalidate(); // session삭제
    return "redirect:/";
  }

  @GetMapping("/info")
  public String info() {
    return "/member/info";
  }

  @GetMapping("/modify")
  public String modify(HttpSession session) {
    //session.invalidate(); // session삭제
    // 회원id(x),  이름 (0), 이메일 안됨(x), 주소(0)
    return "/member/modify";
  }
}
