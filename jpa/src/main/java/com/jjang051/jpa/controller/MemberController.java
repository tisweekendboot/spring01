package com.jjang051.jpa.controller;

import com.jjang051.jpa.entity.CustomUser;
import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.entity.Role;
import com.jjang051.jpa.service.MemberService;
import java.security.Principal;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@Log4j2
public class MemberController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager; // 얘를 통해 세션값 변경해야함

  @GetMapping("/login")
  public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String exception, Model model) {
    model.addAttribute("error", error);
    model.addAttribute("exception", exception);
    return "member/login";
  }

  @GetMapping("/join")
  public String join(Model model) {
    model.addAttribute("member", new Member());
    return "member/join";
  }

  @PostMapping("/join")
  public String joinProcess(@Valid Member member, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("member", member);
      log.info("error있음");
      return "member/join";
    }
    member.setPassword(passwordEncoder.encode(member.getPassword()));
    member.setRole(Role.MEMBER);
    member.setEnabled(true);
    memberService.register(member);
    return "redirect:/member/login";
  }

  @GetMapping("/info")
  public String info() {
    return "member/info";
  }

  @GetMapping("/modify")
  //@ResponseBody
  public String modify(Model model, @AuthenticationPrincipal CustomUser customUser) {
    // seccion안에 SecurityContextHolder  안에 Authentucation에 담아서 저장 찾기가 힘들다.
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    log.info("auth.getPrincipal()==={}", auth.getPrincipal());
    Member member = customUser.getMember();
    log.info(member.getUsername());
    log.info(member.getName());
    model.addAttribute("member", member);

    return "member/modify";
  }

  @PostMapping("/modify")
  public String modifyProcess(Member member, @AuthenticationPrincipal CustomUser customUser) {
    Member authMember = customUser.getMember();
    String authUserPassword = authMember.getPassword();
    if (passwordEncoder.matches(member.getPassword(), authUserPassword)) {
      log.info("맞다");
      memberService.updateWithPassword(member);

      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);

      return "redirect:/";
    }
    return "member/modify";
  }

  @GetMapping("/delete")
  public String delete(Model model, @AuthenticationPrincipal CustomUser customUser) {
    Member member = customUser.getMember();
    model.addAttribute("member", member);
    return "member/delete";
  }

  @PostMapping("/delete")
  public String deleteProcess(Member member, @AuthenticationPrincipal CustomUser customUser) {
    Member authMember = customUser.getMember();
    String authUserPassword = authMember.getPassword();
    if (passwordEncoder.matches(member.getPassword(), authUserPassword)) {
      log.info("맞다");
      memberService.deleteById(member.getUsername());
      SecurityContextHolder.clearContext();
      return "redirect:/";
    }
    return "member/modify";
  }
}
