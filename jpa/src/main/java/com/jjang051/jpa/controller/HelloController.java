package com.jjang051.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController { // new HelloController()

  @RequestMapping("/")
  public String hello() {
    return "index/index"; // /WEB-INF/index.jsp
  }
}
