package com.yosep.spring.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yosep.spring.member.entity.Member;
import com.yosep.spring.member.entity.QMember;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MemberRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    QMember member = QMember.member;

    public MemberRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Member.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Member> findByName(String name) {
        return jpaQueryFactory.selectFrom(member)
                .where(member.name.eq(name))
                .fetch();
    }
}
