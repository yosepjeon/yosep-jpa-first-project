package com.yosep.spring.order.service;

import com.yosep.spring.common.Address;
import com.yosep.spring.common.exception.NotEnoughStockException;
import com.yosep.spring.member.entity.Member;
import com.yosep.spring.member.repository.MemberJpaRepository;
import com.yosep.spring.order.entity.Order;
import com.yosep.spring.order.entity.OrderStatus;
import com.yosep.spring.order.repository.OrderJpaRepository;
import com.yosep.spring.order.repository.OrderRepository;
import com.yosep.spring.product.entity.Book;
import com.yosep.spring.product.entity.Item;
import com.yosep.spring.product.repository.ItemJpaRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
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
    OrderJpaRepository orderJpaRepository;
    @Autowired
    MemberJpaRepository memberJpaRepository;
    @Autowired
    ItemJpaRepository itemJpaRepository;

    @Test
    public void 상품주문() throws Exception {
        // Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        // When
        Long orderId = orderService.order(member.getId(),item.getId(),orderCount);

        // Then
        Order getOrder = orderJpaRepository.findById(orderId).get();

        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus(),"상품 주문시 상태는 ORDER");
        Assertions.assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        Assertions.assertEquals(10000 * 2, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
        Assertions.assertEquals(8, item.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        // Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        // When & Then
        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), item.getId(), orderCount);
        });
    }

    @Test
    public void 주문취소() {
        // Give
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        Assertions.assertEquals(8, item.getStockQuantity());

        // When
        orderService.cancelOrder(orderId);

        // Then
        Order getOrder = orderJpaRepository.findById(orderId).get();

        Assertions.assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 CANCEL이다.");
        Assertions.assertEquals(10, item.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");

    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "경기", "123-123"));
        return memberJpaRepository.save(member);
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        return itemJpaRepository.save(book);
    }
}
