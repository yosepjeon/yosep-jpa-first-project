package com.yosep.spring.member.repository;

import com.yosep.spring.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member ,Long> {

}
