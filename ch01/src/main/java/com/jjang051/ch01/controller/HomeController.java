package com.jjang051.ch01.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// spring은 annotation 기반으로 동작한다.
// Controller 가 있으면 안내 데스크 역할을 한다.
@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "/home";
  }

  @GetMapping("/hello")
  public String hello(
    @RequestParam("name") String name,
    @RequestParam("password") String password,
    Model model
  ) {
    if (name.equals("jjang051") && password.equals("1234")) {
      System.out.println("로그인 성공 메세지 보내주기");
      model.addAttribute("loginMsg", "로그인 성공");
    } else {
      System.out.println("로그인 실패 메세지 보내주기");
      model.addAttribute("loginMsg", "로그인 실패");
    }
    return "/hello";
  }

  @GetMapping("/hello03")
  public String hello03(String name, String password, int age) {
    return "/hello";
  }

  @GetMapping("/hello02")
  @ResponseBody
  public String hello02() {
    return "/hello02";
  }
}
