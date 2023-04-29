package com.jjang051.jpa.repository;

import com.jjang051.jpa.entity.Member;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
  @Query(value = "update member set name = :#{#paramMember.name} where username = :#{#paramMember.username}", nativeQuery = true)
  @Modifying
  @Transactional
  public void updateWithPassword(@Param("paramMember") Member member);
}
