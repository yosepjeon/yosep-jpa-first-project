package com.yosep.spring.member.service;

import com.yosep.spring.member.entity.Member;
import com.yosep.spring.member.repository.MemberJpaRepository;
import com.yosep.spring.member.repository.MemberRepository;
import com.yosep.spring.member.repository.MemberRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberJpaRepository memberRepository;
    private final MemberRepositorySupport memberRepositorySupport;

    @Autowired
    public MemberService(MemberJpaRepository memberRepository, MemberRepositorySupport memberRepositorySupport) {
        this.memberRepository = memberRepository;
        this.memberRepositorySupport = memberRepositorySupport;
    }

    @Transactional(readOnly = false)
    public Long join(Member member) {
        return memberRepository.save(member) != null ? member.getId() : -1;
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {

        return memberRepository.findById(memberId);
    }
}
