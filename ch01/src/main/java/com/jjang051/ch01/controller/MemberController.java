package com.jjang051.ch01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

  @GetMapping("/member/login")
  public String login() {
    return "/member/login";
  }

  @PutMapping("/member/login")
  @ResponseBody
  public String loginPut(String userId, String userPw) {
    if (userId.equals("jjang051") && userPw.equals("1234")) {
      return "login success";
    } else {
      return "login fail";
    }
  }

  @PostMapping("/member/login")
  public String loginProcess(
    @RequestParam(
      name = "userId",
      defaultValue = "noname",
      required = false
    ) String userId,
    @RequestParam(
      name = "userPw",
      defaultValue = "nopassword",
      required = true
    ) String userPw
  ) {
    System.out.println("userId===" + userId);
    System.out.println("userPw===" + userPw);
    if (userId.equals("jjang051") && userPw.equals("1234")) {
      return "/member/loginOk";
    } else {
      return "/member/loginFail";
    }
  }
}
