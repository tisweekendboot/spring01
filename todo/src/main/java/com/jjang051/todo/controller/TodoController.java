package com.jjang051.todo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/todo")
public class TodoController {

  @GetMapping("/index")
  public String home() {
    return "/todo/index";
  }
}
