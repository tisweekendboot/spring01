package com.jjang051.jpa;

import com.jjang051.jpa.entity.Board;
import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.entity.Role;
import com.jjang051.jpa.repository.BoardRepository;
import com.jjang051.jpa.repository.MemberRepository;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Log4j2
class JpaApplicationTests {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void createMember() {
    Member member = new Member();
    member.setUsername("jjang051");
    member.setPassword(passwordEncoder.encode("1234"));
    member.setName("장성호");
    member.setRole(Role.ADMIN);
    member.setEnabled(true);
    memberRepository.save(member);
  }

  @Test
  public void getWriterBoard() {
    List<Board> boardList = boardRepository.findByWriter("정형돈");
    log.info("==================");
    log.info(boardList.get(0).toString());
  }

  @Test
  void contextLoads() {}
}
