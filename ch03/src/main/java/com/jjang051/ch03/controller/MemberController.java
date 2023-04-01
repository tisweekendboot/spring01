package com.jjang051.ch03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {

  @GetMapping("/member/login")
  public String login() {
    return "/member/login";
  }

  @GetMapping("/member/loginSuccess")
  public String loginSuccess(String msg, Model model) {
    model.addAttribute("successMsg", msg);
    return "/member/loginSuccess";
  }

  @GetMapping("/member/loginProcess")
  public String loginProcess(
    String userId,
    String userPw,
    RedirectAttributes redirectAttributes
  ) {
    if (userId.equals("jjang051") && userPw.equals("1234")) {
      redirectAttributes.addAttribute("msg", "로그인 성공");
      redirectAttributes.addFlashAttribute("flashMsg", "장성호님 안녕하세요.");
      return "redirect:/member/loginSuccess";
    } else {
      return "redirect:/member/login";
    }
  }
}
