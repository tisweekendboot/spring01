package com.jjang051.jpa.controller;

import com.jjang051.jpa.entity.Member03;
import com.jjang051.jpa.entity.Role;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Log4j2
public class MemberController {

  @Autowired
  PasswordEncoder passwordEncoder;

  @GetMapping("/login")
  public String login() {
    return "/member/login";
  }

  @GetMapping("/join")
  public String join(Model model) {
    model.addAttribute("member", new Member03());
    return "/member/join";
  }

  @PostMapping("/join")
  public String joinProcess(Model model, Member03 member) {
    String rawPassword = member.getPassword();
    String encodingPassWord = passwordEncoder.encode(rawPassword);
    log.info("encodingPassWord==={}", encodingPassWord);
    member.setPassword(encodingPassWord);
    member.setRole(Role.MEMBER);
    member.setEnabled(true);

    return "/member/join";
  }
}
