package com.jjang051.ch01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

  @GetMapping("/rest/home")
  public String home() {
    return "<h1>rest home</h1>";
  }
}
