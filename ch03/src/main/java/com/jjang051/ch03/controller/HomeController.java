package com.jjang051.ch03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  //@RequestMapping("/")
  @GetMapping("/")
  public String home(Model model, String msg, String contents, int gender) {
    model.addAttribute("msg", msg);
    if (gender == 1) {
      model.addAttribute("gender", "남자");
    } else if (gender == 2) {
      model.addAttribute("gender", "여자");
    }
    model.addAttribute("contents", contents);
    return "/index/index";
  }
}
