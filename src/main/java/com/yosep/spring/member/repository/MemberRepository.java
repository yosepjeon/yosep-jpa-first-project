package com.yosep.spring.member.repository;

import com.yosep.spring.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    private final MemberRepositorySupport memberRepositorySupport;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public MemberRepository(MemberRepositorySupport memberRepositorySupport) {
        this.memberRepositorySupport = memberRepositorySupport;
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return memberRepositorySupport.findByName(name);
    }
}
