package com.jjang051.ch05.controller;

import com.jjang051.ch05.dto.MemberDto;
import com.jjang051.ch05.service.MemberService;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
  public String join() {
    return "/member/join";
  }

  @PostMapping("/join")
  public String joinProcess(MemberDto memberDto) {
    log.info("============memberDto===============");
    log.info(memberDto);
    int result = memberService.insertMember(memberDto);
    if (result > 0) {
      return "redirect:/";
    }
    return "/member/join";
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
    return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(resultMap);
  }
}