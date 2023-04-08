package com.jjang051.ch04.controller;

import com.jjang051.ch04.dao.MemberDao;
import com.jjang051.ch04.dto.MemberDto;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

  @Autowired
  MemberDao memberDao;

  @GetMapping("/join")
  public String join() {
    return "/member/join";
  }

  @PostMapping("/join")
  //@ResponseBody
  public String joinProcess(String userId, String userPw, String userName) {
    MemberDto memberDto = new MemberDto();
    memberDto.setUserId(userId);
    memberDto.setUserName(userName);
    memberDto.setUserPw(userPw);
    memberDao.insertMember(memberDto);
    return "redirect:/member/list";
  }

  @GetMapping("/list")
  public String list(Model model) {
    List<MemberDto> memberList = memberDao.getAllMember();
    model.addAttribute("memberList", memberList);
    return "/member/list";
  }

  @GetMapping("/info")
  public String info(Model model, String userId) {
    log.info("====" + userId);
    MemberDto memberInfo = memberDao.getOneMember(userId);
    model.addAttribute("memberInfo", memberInfo);
    return "/member/info";
  }
}
