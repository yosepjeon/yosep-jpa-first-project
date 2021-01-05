package com.yosep.spring.order.service;

import com.yosep.spring.common.Address;
import com.yosep.spring.member.entity.Member;
import com.yosep.spring.member.repository.MemberJpaRepository;
import com.yosep.spring.order.repository.OrderJpaRepository;
import com.yosep.spring.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderJpaRepository orderRepository;
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void 상품주문() throws Exception {
        // Given
//        Member member = c
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "경기", "123-123"));
        return memberJpaRepository.save(member);
    }
}
