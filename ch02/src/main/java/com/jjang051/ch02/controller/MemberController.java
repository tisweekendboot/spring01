package com.jjang051.ch02.controller;

import com.jjang051.ch02.dto.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
public class MemberController {

  @GetMapping("/member/join")
  @ResponseBody
  public String join(String name, String password) {
    Member member = new Member();
    member.setName(name);
    member.setPassword(password);
    log.info(member);
    member.toString();
    return "/member/join";
  }

  @GetMapping("/member/join02")
  @ResponseBody
  public String join02(Member member) {
    log.info(member);
    return "/member/join";
  }
}
