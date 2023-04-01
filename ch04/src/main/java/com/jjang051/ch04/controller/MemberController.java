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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
public class MemberController {

  @Autowired
  MemberDao memberDao;

  @GetMapping("/member/join")
  public String join() {
    return "/member/join";
  }

  @PostMapping("/member/join")
  //@ResponseBody
  public String joinProcess(String userId, String userPw, String userName) {
    MemberDto memberDto = new MemberDto();
    memberDto.setUserId(userId);
    memberDto.setUserName(userName);
    memberDto.setUserPw(userPw);
    memberDao.insertMember(memberDto);
    return "redirect:/member/list";
  }

  @GetMapping("/member/list")
  public String list(Model model) {
    List<MemberDto> memberList = memberDao.getAllMember();
    model.addAttribute("memberList", memberList);
    return "/member/list";
  }
}
