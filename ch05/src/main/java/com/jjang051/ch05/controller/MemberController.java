package com.jjang051.ch05.controller;

import com.jjang051.ch05.dto.MemberDto;
import com.jjang051.ch05.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

  @Autowired
  MemberService memberService;

  @GetMapping("/join")
  public String join(MemberDto memberDto) {
    memberService.insertMember(memberDto);
    return "/member/join";
  }
}
